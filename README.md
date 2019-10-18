<h1>LevaJeito</h1>

<p>Atividade com o intuito de utilizar hash para mapeamento de pastas.</p>

<h2>O que foi feito</h2>

<p>Uma aplicação foi feita com o intuito de realizar o mapeamento de um diretório. Esse mapeamento foi feito utilizando Hash. Portanto, a aplicação sabe quando algum arquivo foi inserido, removido ou alterado.</p>
<p>Há três funções que o usuário pode pedir para o programa realizar, são elas: iniciar o mapeamento, verificar se houve alterações e excluir o mapeamento.</p>

<h2>Como foi feito</h2>

<p>Para o desenvolvimento da aplicação, utilizou-se Java 11.</p>
<p>Inicialmente, pega-se, recursivamente, todos os arquivos de um diretório passado por argumento. Após isso, calcula uma hash para cada arquivo, sendo ela hash ou hmac utilizando o SHA256. Todas as hash's são salvas dentro do diretório que se desejava mapear dentro de uma pasta oculta chamada 'guarda'.</p>

<ul>
  <li> Para executar uma inicialização, utiliza-se a tag -i </li>
  <li> Para executar um rastreamento, utiliza-se a tag -t</li>
  <li> Para executar uma exclusão, utiliza-se a tag -x</li>
</ul>

<p>Logo em seguida, deve ser inserido o caminho absoluto da pasta que se deseja realizar tal operação.</p>
<p>Para indicar qual  o tipo de hash ou hmac, utiliza-se a tag -hash ou -hmac. Se a tag escolhida for -hmac, então logo após esta deve-se inserir a senha que se deseja utilizar.</p>
<p>Por fim, se deseja salvar a saída do algoritmo em um arquivo .txt, então utiliza-se a tag -o e, logo em seguida, coloca-se o caminho do arquivo de texto que se deseja salvar.</p>
<p>Sobre como foi feito a hash, utilizou-se o openssl do Linux. Ou seja, quando o programa vai calcular a hash, ele manda para o terminal o caminho do arquivo utilizando o openssl e pega a hash que é retornada.</p>
<p>A seguir, segue um exemplo de como a classe de execução de comandos shell trabalha para calcular a hash de um arquivo.</p>
<img src="https://user-images.githubusercontent.com/32347176/66261120-47628a00-e79e-11e9-85fb-fc17f98c081c.png">
<h2>Dificuldades</h2>

<p>Duas dificuldades foram as principais no decorrer do desenvolvimento da aplicação.</p>
<p>A primeira foi como acessar o sistema para pegar os diretórios recursivamente.</p>
<p>A segunda foi como calcular a hash, qual a biblioteca utilizar em Java para calcular ela. Para suprir essa necessidade, procurou-se uma classe em Java que realizasse comandos do terminal Linux. A partir dela, adaptou-se para a necessidade de executar comandos do openssl.</p>

<h2>Testes</h2>

<p>Para entender o funcionamento da aplicação, segue alguns passos:</p>
<p> Primeiramente, baixe o Guarda.jar e coloque-o na Área de Trabalho.</p>
<img src="https://user-images.githubusercontent.com/32347176/66260320-9656f200-e793-11e9-96af-0c359d0b5e79.png">

<h3> Teste para inicialização </h3>
<p>Vale salientar que esses testes foram realizados utilizando o ambiente Ubuntu 18.</p>
<p>Teste utilizando -hash.</p>
<img src="https://user-images.githubusercontent.com/32347176/66260352-05cce180-e794-11e9-90ac-355e77186bdc.png">
<p>Arquivo oculto salvando as hash's.</p>
<img src="https://user-images.githubusercontent.com/32347176/66260924-71ff1380-e79b-11e9-8e86-1560b4cd902a.png">
<p>Teste utilizando -hmac.</p>
<img src="https://user-images.githubusercontent.com/32347176/66260941-bdb1bd00-e79b-11e9-9baa-bfae6576eac2.png">
<p>Arquivo oculto salvando as hash's com senha.</p>
<img src="https://user-images.githubusercontent.com/32347176/66260945-cdc99c80-e79b-11e9-9af5-0a0b4f2faf5e.png">

<h3>Teste para rastreamento</h3>

<p>Teste utilizando -hash. Com as seguintes alteraçoes:</p>
<ul>
  <li>Inserindo 'Arquivo X.odt' na subpasta mais profunda.</li>
  <li>Alterando conteúdo do 'Arquivo 1.txt' na raiz da pasta.</li>
  <li>Removendo 'Arquivo 3.pdf' na subpasta mais profunda.</li>
</ul>
<img src="https://user-images.githubusercontent.com/32347176/66261013-cbb40d80-e79c-11e9-875b-070686005f45.png">
<p>Teste utilizando -hmac.</p>
<img src="https://user-images.githubusercontent.com/32347176/66261015-cf479480-e79c-11e9-92b0-621d241200ea.png">

<h3>Teste para exclusão</h3>
<p>A execução dessa linha de comando apaga a pasta oculta .guarda. Portanto, tanto faz se fez o rastreamento usando -hmac ou -hash.</p>
<img src="https://user-images.githubusercontent.com/32347176/66261043-3107fe80-e79d-11e9-8d1f-8c97ac880de6.png">

<h3>Teste utilizando tag de saída</h3>
<p>Para que o relatório seja guardado em um arquivo txt, basta adicionar a tag -o e logo em seguida adicionar o caminho absoluto do arquivo em que se deseja salvar. O código executado antes deste foi o do passo de Inicialização utilizando o -hash.</p>
<img src="https://user-images.githubusercontent.com/32347176/66261077-9f4cc100-e79d-11e9-9401-2924fd1b511e.png">

<h1>Referências</h1>
<p>Classe LocalShell foi desenvolvida por outra pessoa. Disponível em <a href="https://www.devmedia.com.br/executando-shell-scripts-utilizando-java/26494" target="window">DevMedia</a>.</p>

<h1>Autor</h1>
<p>Aroldo Felix (junioraroldo37@gmail.com)</p>
