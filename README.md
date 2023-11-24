# HalfMouthBeerApp

Projeto utilizando Kotlin Multiplatform Mobile(KMM) para exibir informações remotas de monitoramento
de equipamentos da crevejaria artesanal HalfMouth.

## O que é Kotlin Multiplatform Mobile (KMM)

Trata-se de uma feature do Kotlin, criada pela JetBrains, que permite compartilhar código comum
entre múltiplas plataformas (JavaScript, iOS, Android, desktop, etc).
A premissa é que você pode escrever sua lógica de negócio apenas uma vez em Kotlin e usar esse
código tanto no Android quanto no iOS.
Porém, ainda temos a necessidade de escrever códigos de UI específicos de acordo com cada plataforma

![KMM-image](images/funny.png)

## Como funciona?

O KMM é flexível o suficiente para permitir que o desenvolvedor diferencie a implementação de API
específica do Android e iOS sempre que necessário.
Ele usa o mecanismo expect/actual para conseguir isso.

![KMM-image](images/expect-actual.png)

Esse mecanismo é parecido com uma interface, onde o módulo compartilhado define uma declaração com a
palavra chave expect e os módulos de cada plataforma proveem a declaração com a palavra chave actual
escritos em Kotlin (e posteriormente compilados para Swift no caso do iOS).
Depois que o módulo compartilhado é criado, o resultado final do módulo compartilhado é o arquivo
.framework para iOS e o arquivo .jar para Android.
Então, logicamente falando, seus projetos Android e iOS nem sabem que o módulo compartilhado está
escrito em Kotlin, para eles é apenas mais uma dependência.

## Integração com o framework Ktor

Ktor é uma estrutura para construir facilmente diversos aplicativos.
Os aplicativos precisam ser assíncronos para fornecer a melhor experiência aos usuários, e as
corrotinas Kotlin fornecem recursos incríveis para fazer isso de maneira fácil e direta.
O objetivo do Ktor é fornecer uma estrutura multiplataforma ponta a ponta para aplicações
conectadas.”
Com o a integração do Ktor, foi possível realizar requisições à Plataforma ThingSpeak para aquisição
de dados;

### Ktor para Multiplataforma

Com o uso do Ktor, podem-se criar o nosso próprio cliente HTTP para interagir com servidores
remotos, normalmente usa-se um plugin de negociação e serialização de conteúdo para analisar a
resposta de rede bruta em um Data Transfer Object utilizável. OkHttp é um cliente de rede popular
usado extensivamente pela comunidade Android, ele fornece um controle mais preciso sobre as
interações de rede, da mesma forma, o cliente Darwin Engine é usado em iOS/Nativo para interações de
rede.

## Tecnologias Utilizadas

- Kotlin Multiplatform Mobile(KMM);
- Compose Multiplatform;
- Compose NAvigation;
- Material3 Theme;
- Compartilhamento de recursos (Share Resources: Strings e Imagens) entre Andoid e iOS usando KMM;
- DynamicColor utilizando MaterialTheme;
- Ktor library;
- Shimmer para Indicação de Loading;

## Futuras Integrações

- Firebase Authentication;
- Tela de Exibição dos Dados e Gráficos;
- Banco de Dados Local;

## Tela do Aplicativo

Tela Inicial com o Logo da Cervejaria:
![KMM-image](images/telainicial.png)

## Vantagens do KMM

- Lógica de negócios compartilhada em grande parte independente da plataforma;
- A interoperabilidade com outras linguagens nos permite usar código Kotlin em Java, Swift ou
  Objective-C e vice-versa;
- Mecanismo expect/actual para definir a estrutura em código comum e fornecer a implementação em
  diretórios específicos da plataforma;
- Capacidade de usar bibliotecas específicas da plataforma no projeto KMP usando Gradle (para
  Android) ou Cocoapods (para iOS);
- Possibilidade futura de compartilhar código de UI também, graças ao Jetpack Compose e Compose for
  Desktop.

## Desvantagens do KMM

- Falta de bibliotecas, ferramentas imaturas;
- Comunidade relativamente menor em comparação com React Native ou Flutter;
- Novo sistema de construção e linguagem a ser aprendida pelos desenvolvedores iOS;
