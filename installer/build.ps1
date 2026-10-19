$projeto = Split-Path -Parent $PSScriptRoot
$jpackage = Get-Command jpackage -ErrorAction SilentlyContinue

if (-not $jpackage -and $env:JAVA_HOME) {
    $caminho = Join-Path $env:JAVA_HOME "bin\jpackage.exe"

    if (Test-Path $caminho) {
        $jpackage = $caminho
    }
}

if (-not $jpackage) {
    throw "jpackage não encontrado. Configure o JAVA_HOME ou adicione o JDK ao PATH."
}

$destinoHost = "$projeto\installer\SMD-Host"
$destinoAgent = "$projeto\installer\SMD-Agent"

$zipHost = "$projeto\installer\SMD-Host-Windows.zip"
$zipAgent = "$projeto\installer\SMD-Agent-Windows.zip"

Write-Host "Compilando..."

Set-Location "$projeto\agent"
mvn clean package

if ($LASTEXITCODE -ne 0) {
    throw "Erro ao compilar o Agent!"
}

Set-Location "$projeto\host"
mvn clean package

if ($LASTEXITCODE -ne 0) {
    throw "Erro ao compilar o Agent!"
}

Set-Location $projeto

Write-Host "Compilação concluída!"

Write-Host "Removendo executáveis antigos..."

if (Test-Path $destinoHost) {
    Remove-Item $destinoHost -Recurse -Force
}

if (Test-Path $destinoAgent) {
    Remove-Item $destinoAgent -Recurse -Force
}

Write-Host "Gerando executáveis..."

& $jpackage `
    --type app-image `
    --name SMD-Host `
    --input "$projeto\host\target" `
    --main-jar SMD-Host.jar `
    --main-class MainServer `
    --dest "$projeto\installer" `
    --win-console

if ($LASTEXITCODE -ne 0) {
    throw "Erro ao gerar o executável do Host!"
}

& $jpackage `
    --type app-image `
    --name SMD-Agent `
    --input "$projeto\agent\target" `
    --main-jar SMD-Agent.jar `
    --main-class MainAgent `
    --dest "$projeto\installer" `
    --win-console

if ($LASTEXITCODE -ne 0) {
    throw "Erro ao gerar o executável do Agent!"
}

Write-Host "Executáveis gerados com sucesso!"

Write-Host "Compactando executáveis..."

Compress-Archive `
    -Path "$projeto\installer\SMD-Host" `
    -DestinationPath $zipHost `
    -Force `
    -ErrorAction Stop

Compress-Archive `
    -Path "$projeto\installer\SMD-Agent" `
    -DestinationPath $zipAgent `
    -Force `
    -ErrorAction Stop

Write-Host "Build e compactação concluídos com sucesso!"