$ErrorActionPreference = "Stop"

$projeto = Split-Path -Parent $PSScriptRoot
$jpackage = Get-Command jpackage -ErrorAction SilentlyContinue

if (-not $jpackage -and $env:JAVA_HOME) {
    $caminho = Join-Path $env:JAVA_HOME "bin\jpackage.exe"

    if (Test-Path $caminho) {
        $jpackage = $caminho
    }
}

if (-not $jpackage) {
    $caminho = "C:\Program Files\Java\jdk-21\bin\jpackage.exe"

    if (Test-Path $caminho) {
        $jpackage = $caminho
    }
}

if (-not $jpackage) {
    throw "jpackage não encontrado. Verifique a instalação do JDK."
}

$installer = Join-Path $projeto "installer"

$destinoHost = Join-Path $installer "SMD-Host"
$destinoAgent = Join-Path $installer "SMD-Agent"

$zipHost = Join-Path $installer "SMD-Host-Windows.zip"
$zipAgent = Join-Path $installer "SMD-Agent-Windows.zip"

$inputHost = Join-Path $installer "temp\host"
$inputAgent = Join-Path $installer "temp\agent"

$jarHost = Join-Path $projeto "host\target\SMD-Host.jar"
$jarAgent = Join-Path $projeto "agent\target\SMD-Agent.jar"

Write-Host "Compilando Agent..."

Push-Location $projeto

try {
    Set-Location "$projeto\agent"
    mvn clean package

    if ($LASTEXITCODE -ne 0) {
        throw "Erro ao compilar o Agent!"
    }

    Write-Host "Compilando Host..."

    Set-Location "$projeto\host"
    mvn clean package

    if ($LASTEXITCODE -ne 0) {
        throw "Erro ao compilar o Host!"
    }

    Set-Location $projeto

    Write-Host "Compilação concluída!"

    if (-not (Test-Path $jarHost)) {
        throw "JAR do Host não encontrado!"
    }

    if (-not (Test-Path $jarAgent)) {
        throw "JAR do Agent não encontrado!"
    }

    Write-Host "Removendo executáveis antigos..."

    if (Test-Path $destinoHost) {
        Remove-Item $destinoHost -Recurse -Force -ErrorAction Stop
    }

    if (Test-Path $destinoAgent) {
        Remove-Item $destinoAgent -Recurse -Force -ErrorAction Stop
    }

    Write-Host "Preparando arquivos para empacotamento..."

    $temp = Join-Path $installer "temp"

    if (Test-Path $temp) {
        Remove-Item $temp -Recurse -Force -ErrorAction Stop
    }

    New-Item -ItemType Directory -Path $inputHost -Force | Out-Null
    New-Item -ItemType Directory -Path $inputAgent -Force | Out-Null

    Copy-Item $jarHost $inputHost -ErrorAction Stop
    Copy-Item $jarAgent $inputAgent -ErrorAction Stop

    Write-Host "Gerando SMD-Host.exe..."

    & $jpackage `
        --type app-image `
        --name SMD-Host `
        --input $inputHost `
        --main-jar SMD-Host.jar `
        --main-class MainServer `
        --dest $installer `
        --win-console

    if ($LASTEXITCODE -ne 0) {
        throw "Erro ao gerar o executável do Host!"
    }

    Write-Host "Gerando SMD-Agent.exe..."

    & $jpackage `
        --type app-image `
        --name SMD-Agent `
        --input $inputAgent `
        --main-jar SMD-Agent.jar `
        --main-class MainAgent `
        --dest $installer `
        --win-console

    if ($LASTEXITCODE -ne 0) {
        throw "Erro ao gerar o executável do Agent!"
    }

    Write-Host "Executáveis gerados com sucesso!"

    Write-Host "Compactando executáveis..."

    Compress-Archive `
        -Path $destinoHost `
        -DestinationPath $zipHost `
        -Force `
        -ErrorAction Stop

    Compress-Archive `
        -Path $destinoAgent `
        -DestinationPath $zipAgent `
        -Force `
        -ErrorAction Stop

    Write-Host "Build e compactação concluídos com sucesso!"
}
finally {
    if (Test-Path (Join-Path $installer "temp")) {
        Remove-Item (Join-Path $installer "temp") -Recurse -Force -ErrorAction Stop
    }

    Pop-Location
}