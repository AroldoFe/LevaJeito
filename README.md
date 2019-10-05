<h1>LevaJeito</h1>
<p>Atividade com o intuito de utilizar hash para mapeamento de pastas.</p>

<h2>O que foi feito</h2>
<p>Uma aplicação foi feita com o intuito de realizar o mapeamento de um diretório. Esse mapeamento foi feito utilizando Hash. Portanto, a aplicação sabe quando algum arquivo foi inserido, removido ou alterado.</p>
<p>Há três funções que o usuário pode pedir para o programa realizar, são elas: iniciar o mapeamento, verificar se houve alterações e excluir o mapeamento.</p>
<h2>Como foi feito</h2>
<p>Para o desenvolvimento da aplicação, utilizou-se Java 11.</p>
<p>Inicialmente, pega-se, recursivamente, todos os arquivos de um diretório passado por argumento. Após isso, calcula uma hash para cada arquivo, sendo ela hash ou hmac utilizando o SHA256. Todas as hash's são salvas dentro do diretório que se desejava mapear dentro de uma pasta oculta chamada 'guarda'.</p>
<p>Para executar uma </p>
<p></p>
<h2>Dificuldades</h2>

<h2>Testes</h2>
<p>Para entender o funcionamento da aplicação, segue alguns passos:</p>
<p> Primeiramente, baixe o Guarda.jar e coloque-o na Área de Trabalho.</p>
<h3> Teste para inicialização </h3>
![Arquivo Guarda.jar na Área de Trabalho](https://user-images.githubusercontent.com/32347176/66260283-03b65300-e793-11e9-8f4f-6fece14163fc.png)

<h1>Referências</h1>
<p>Classe LocalShell foi desenvolvida por outra pessoa. Disponível em <a href="https://www.devmedia.com.br/executando-shell-scripts-utilizando-java/26494" target="window">DevMedia</a></p>

<h1>Autor</h1>
<p>Aroldo Felix (junioraroldo37@gmail.com)</p>
