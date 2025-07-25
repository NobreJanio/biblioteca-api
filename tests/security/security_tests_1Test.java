/*
 * Testes de Segurança - Arquivo 1/2
 * Gerado automaticamente pela TestAI
 * Repositório: NobreJanio/biblioteca-api
 * Data: 2025-07-25T00:24:26.293Z
 * Linguagem: java
 * Framework: OWASP ZAP
 */

# File: zap_scan.py
import os
import subprocess

target_url = 'http://localhost:8080' # substituir com url do aplicativo
zap_path = '/path/to/zap' # substituir com diretório de instalação do ZAP

# Iniciar ZAP
subprocess.Popen([zap_path, '-daemon'])

# Rodar o scan passivo
subprocess.call(['python', 'zap-baseline.py', '-t', target_url])

# Se você também tem sessões de login/logout ou autenticação, fortalecer seu teste de segurança com spider scan, que rastreia a aplicação.
subprocess.call(['python', 'zap-full-scan.py', '-t', target_url])

# Após a execução desses testes, você terá um relatório em formato HTML que detalha as vulnerabilidades descobertas. 
