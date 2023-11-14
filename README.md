# HalfMouthBeerApp
Projeto utilizando Kotlin Multiplatform Mobile(KMM) para exibir informações remotas de monitoramento de 
equipamentos da crevejaria artesanal HalfMouth.

## O que é Kotlin Multiplatform Mobile (KMM)
Trata-se de uma feature do Kotlin, criada pela JetBrains, que permite compartilhar código comum entre múltiplas plataformas (JavaScript, iOS, Android, desktop, etc).
A premissa é que você pode escrever sua lógica de negócio apenas uma vez em Kotlin e usar esse código tanto no Android quanto no iOS. 
Porém, ainda temos a necessidade de escrever códigos de UI específicos de acordo com cada plataforma

## Como funciona?
O KMM é flexível o suficiente para permitir que o desenvolvedor diferencie a implementação de API específica do Android e iOS sempre que necessário. 
Ele usa o mecanismo expect/actual para conseguir isso.

[KMM-image](https://miro.medium.com/v2/resize:fit:1400/0*QI8feaK3gPPB6imo)

Esse mecanismo é parecido com uma interface, onde o módulo compartilhado define uma declaração com a palavra chave expect e os módulos de cada plataforma proveem a declaração com a palavra chave actual escritos em Kotlin (e posteriormente compilados para Swift no caso do iOS).
Depois que o módulo compartilhado é criado, o resultado final do módulo compartilhado é o arquivo .framework para iOS e o arquivo .jar para Android. 
Então, logicamente falando, seus projetos Android e iOS nem sabem que o módulo compartilhado está escrito em Kotlin, para eles é apenas mais uma dependência.

