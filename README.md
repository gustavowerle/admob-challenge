# Admob Challenge
Neste projeto, desenvolvi uma biblioteca em Kotlin para integrar o Google AdMob e exibir banners de anúncios utilizando Jetpack Compose. 
Para demonstrar a aplicação prática da biblioteca, utilizei o app de exemplo [Jetnews](https://github.com/android/compose-samples), 
adaptando sua interface para ilustrar os cenários de exibição dos anúncios. 
Sendo assim, o projeto contém dois módulos, o primeiro sendo o módulo app que contém a implementação do aplicativo JetNews, 
e o segundo sendo o módulo advertisement que contém a implementação da biblioteca de integração com o AdMob.

## Screenshots

<img src="screenshots/main_screen_banner.png" alt="Screenshot">
<img src="screenshots/article_screen_banner.png" alt="Screenshot">

## Módulo advertisement

### BannerAd
BannerAd é um componente desenvolvido em Jetpack Compose para exibição de anúncios utilizando o Google AdMob. 
Nesse componente, podemos configurar o modificador para alinhar o componente na UI em que ele for utilizado, 
configurar o tipo do banner INLINE_ADAPTIVE, COLLAPSIBLE_TOP ou COLLAPSIBLE_BOTTOM e também configurar o 
callback onAdLoaded() para ser executado quando o banner for carregado.

### BannerType
BannerType contém os tipos de banner configurados.

### AdsInitializer
AdsInitializer contém a função initializeAds() para inicializar o AdMob e deve ser chamada na MainActivity do app que for utilizar essa biblioteca.

### Testes
O componente BannerAd conta com testes automatizados de UI. 
