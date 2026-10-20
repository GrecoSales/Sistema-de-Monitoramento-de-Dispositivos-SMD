# SMD — Sistema de Monitoramento de Dispositivos

Sistema de monitoramento de dispositivos desenvolvido em Java, utilizando arquitetura cliente-servidor para coletar e transmitir 
informações de hardware e sistema operacional entre computadores conectados em rede.

## 📖 Visão geral

O **Sistema de Monitoramento de Dispositivos** é capaz de coletar e enviar estatísticas de vários computadores para uma única máquina, 
facilitando a sua inspeção.

O sistema utiliza uma arquitetura cliente-servidor, composta por dois programas independentes: **Host** e **Agent**.

O Host atua como servidor, responsável por receber conexões e informações dos dispositivos monitorados. O Agent é executado nos computadores clientes, 
coletando informações do sistema e transmitindo os dados ao Host.

O projeto também tem como objetivo explorar conceitos de programação orientada a objetos, comunicação em rede, 
arquitetura de software e distribuição de aplicações Java.

## ⚙️ Funcionalidades

### Host (Servidor)

- Inicialização de um servidor TCP para comunicação com os dispositivos.
- Resposta às solicitações de descoberta de servidor via UDP.
- Recebimento de conexões de dispositivos que executam o Agent.
- Recebimento de informações enviadas pelos dispositivos conectados.

### Agent (Cliente)

- Coleta de informações de hardware e sistema operacional.
- Identificação do nome do computador e do sistema operacional.
- Coleta de informações sobre processador, memória RAM e unidades de armazenamento.
- Descoberta automática do servidor na rede utilizando UDP.
- Estabelecimento de conexão TCP com o Host.
- Transmissão das informações coletadas ao servidor.

> **Nota:** O projeto está em desenvolvimento. Atualmente, as funcionalidades são executadas por meio de aplicações de console, sem interface gráfica.

## 🏗️ Arquitetura

O SMD utiliza uma arquitetura cliente-servidor, na qual o Host centraliza a comunicação com os dispositivos que executam o Agent.

A comunicação é realizada utilizando dois protocolos:

- **UDP (User Datagram Protocol):** utilizado para a descoberta do servidor na rede local.
- **TCP (Transmission Control Protocol):** utilizado para estabelecer a conexão e transmitir informações entre o Agent e o Host.

### Fluxo de comunicação

1. O Host é inicializado e fica disponível para receber solicitações de descoberta e conexões.
2. O Agent realiza uma solicitação UDP para localizar o servidor.
3. O Host responde informando seu endereço IP e a porta de comunicação TCP.
4. O Agent utiliza essas informações para estabelecer uma conexão TCP.
5. Após a conexão, o Agent transmite as informações coletadas ao Host.
6. A comunicação permanece ativa enquanto a conexão estiver estabelecida.

### Organização do projeto

O SMD está organizado em dois módulos independentes e um diretório de distribuição:

- **Agent:** responsável pela coleta de informações do dispositivo e comunicação com o servidor.
- **Host:** responsável por receber conexões e informações dos dispositivos monitorados.
- **Installer:** contém o script PowerShell responsável por automatizar a compilação e a geração dos executáveis Windows.

Os módulos Host e Agent possuem projetos Maven independentes, permitindo sua compilação e distribuição separadamente.

## 🛠️ Tecnologias utilizadas

| Tecnologia | Aplicação |
|---|---|
| **Java** | Linguagem principal de desenvolvimento |
| **Maven** | Gerenciamento de dependências e compilação |
| **OSHI** | Coleta de informações de hardware e sistema operacional |
| **TCP/IP** | Comunicação entre servidor e dispositivos |
| **UDP** | Descoberta automática do servidor na rede |
| **Java Sockets** | Implementação da comunicação em rede |
| **Maven Shade Plugin** | Geração de arquivos JAR com dependências |
| **jpackage** | Empacotamento das aplicações para Windows |
| **PowerShell** | Automação da compilação e distribuição |

O projeto utiliza conceitos de programação orientada a objetos e separação de responsabilidades para organizar os componentes responsáveis pela coleta,
configuração e comunicação em rede.

## 📦 Instalação e execução

O SMD disponibiliza duas aplicações independentes para Windows:

- **SMD-Host:** aplicação responsável por receber conexões e informações dos dispositivos.
- **SMD-Agent:** aplicação responsável pela coleta e transmissão de informações do computador.

### Executando os programas

1. Acesse a seção **Releases** deste repositório.
2. Baixe os arquivos `SMD-Host-Windows.zip` e `SMD-Agent-Windows.zip`.
3. Extraia os arquivos ZIP.
4. Execute `SMD-Host.exe` no computador que atuará como servidor.
5. Execute `SMD-Agent.exe` no computador que será monitorado.
6. O Agent tentará localizar o Host na rede e estabelecer a comunicação.

**Importante:**

- Para que a coleta de temperatura dos componentes funcione corretamente, é necessário executar o SMD-Agent com privilégios de administrador.
- O firewall do sistema operacional pode exigir a liberação das portas utilizadas.
- Para a descoberta automática, os dispositivos devem estar em uma rede que permita a comunicação UDP entre eles.
- Não é necessário instalar o Java separadamente para executar os pacotes Windows disponibilizados nas Releases.

### Compilação a partir do código-fonte

Para compilar o projeto, são necessários:

- JDK 21 ou superior, compatível com a configuração do projeto.
- Apache Maven.
- PowerShell e `jpackage` para gerar os pacotes Windows.

O repositório possui um script de automação localizado em:

```text
installer/build.ps1
```

Na raiz do projeto, execute:

```powershell
.\installer\build.ps1
```

O script realiza automaticamente:

1. Compilação do Agent e do Host utilizando Maven.
2. Geração dos arquivos JAR com suas dependências.
3. Empacotamento das aplicações Windows utilizando `jpackage`.
4. Compactação dos programas em arquivos ZIP para distribuição.

Ao final, os pacotes são disponibilizados no diretório `installer`.

## 🚧 Estado do desenvolvimento

O SMD está em desenvolvimento e atualmente possui uma implementação funcional de comunicação cliente-servidor e coleta de informações do sistema.

### Funcionalidades implementadas

- [x] Estruturação dos módulos Host e Agent.
- [x] Coleta de informações de hardware e sistema operacional.
- [x] Descoberta automática do servidor via UDP.
- [x] Estabelecimento de conexões TCP.
- [x] Transmissão de informações entre Agent e Host.
- [x] Compilação e gerenciamento de dependências com Maven.
- [x] Geração de executáveis Windows.
- [x] Automação do processo de build e distribuição.

### Funcionalidades planejadas

- [ ] Desenvolvimento de interface gráfica para Host e Agent.
- [ ] Permitir a conexão simultânea de múltiplos Agents a um único Host.
- [ ] Visualização centralizada dos dispositivos conectados.
- [ ] Atualização e apresentação de métricas de monitoramento.
- [ ] Implementação de alertas relacionados ao estado dos dispositivos.
- [ ] Gerenciamento de conexões e dispositivos autorizados.
- [ ] Aprimoramento da segurança da comunicação entre Host e Agent.

## 📄 Licença

Este projeto está disponibilizado sob a licença GNU GLP v3.0. Consulte o arquivo [LICENSE](LICENSE) para mais informações.