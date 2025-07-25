/*
 * Testes de Performance - Arquivo 1/1
 * Gerado automaticamente pela TestAI
 * Repositório: NobreJanio/biblioteca-api
 * Data: 2025-07-25T00:23:56.105Z
 * Linguagem: java
 * Framework: JMeter
 */

Como um especialista em testes, prefiro usar JMeter para escrever testes de desempenho já que a pergunta solicitou utilizando a ferramenta JMeter. Como JMeter é uma ferramenta gráfica e não é baseada em código, os testes são criados por meio de uma interface de usuário e não é possível gerar um "código" específico. Entretanto, eu posso fornecer uma descrição detalhada de como configurar testes de desempenho utilizando o JMeter.

Aqui estão algumas direções de como você pode configurar um teste de desempenho no JMeter para avaliar o tempo de resposta, uso da memória, throughput e estresse:

Tempo de Resposta:
1. Abra o JMeter.
2. Clique com o botão direito no "Test Plan", vá em "Add" -> "Threads (Users)" -> "Thread Group".
3. No "Thread Group", defina o número de Threads (usuários).
4. Clique com o botão direito no "Thread Group", vá até "Add" -> "Sampler" -> "HTTP Request".
5. No "HTTP Request", forneça detalhes do servidor e da requisição. Você pode definir a URL do servidor e os parâmetros do POST/GET.
6. Clique com o botão direito no "HTTP Request", vá até "Add" -> "Listener" -> "View Results in Table". Esse listener exibirá o tempo de resposta da requisição.

Uso de Memória:
1. O JMeter por si só não pode verificar o uso de memória. Usaremos o plugin "JMeter Plugins Manager".
2. Instale "JMeter Plugins Manager" em "Options" -> "Plugins Manager".
3. Instale o plugin "PerfMon Metrics Collector". 
4. Adicione o "PerfMon Metrics Collector" no plano de teste. E defina o parâmetro "Metric to collect" como "Memory".
5. Agora o JMeter pode coletar e exibir o uso de memória do servidor durante o teste.

Throughput:
1. JMeter possui um listener chamado "Summary Report", que fornece o "Throughput".
2. Adicione a opção "Summary Report" ao teste clicando com o botão direito na requisição HTTP e selecione "Add" -> "Listener" -> "Summary Report".

Stress Testing:
1. Você pode fazer stress testing alterando o número de threads e o tempo de execução na seção "Thread Group".
2. Aumente o número de threads (usuários) para um número alto e defina um longo período de execução.
3. Durante esse teste, você pode verificar o tempo de resposta, uso de memória e o throughput para ver como o sistema se comporta sob stress. 

Por favor, note que após configurar esses testes, você deverá executá-los e verificar os resultados na interface do JMeter. Nenhum código é gerado pela criação desses testes no JMeter.