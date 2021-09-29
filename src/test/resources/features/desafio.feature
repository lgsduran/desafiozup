#encoding: utf-8
@desafio
Feature: Desafio Zup - automação Web
  
   Como usuario
   quero buscar produtos em uma loja ecommerce
   para realizar os testes

  Background: 
    Given que estou em um grande portal de comercio online

  @fluxoPrincipal
  Scenario: Buscar por um produto
    When busco por produto
      | ipad pro cellular 128gb wi-fi 4g tela retina 9,7" dourado - apple |
    And confirmo a busca do produto
    Then sistema deve exibir o produto
      | ipad pro cellular 128gb wi-fi 4g tela retina 9,7" dourado - apple |

  @fluxoAlternativo
  Scenario: Encontrar um produto em catalogos
    When acesso "Informática" > "Apple"
    Then sistema deve exibir o produto
      | Cabo de Lightning para USB |

  @fluxoPrincipal
  Scenario: Buscar por um produto
    When busco por produto para adicionar ao carrinho
      | Console PS4 1TB Edição Family |
    Then sistema deve adicionar produto ao carrinho
      | Console PS4 1TB Edição Family |

  @fluxoAlternativo
  Scenario: Encontrar um produto em catalogos
    When acesso "Tv, Áudio e Home Theater" > "TV LED"
    And adiciono produto ao carrinho
      | TV LED Pro 43" LG 43LV300C.AWZ HD com Conversor Digital Integrado 1 USB 1 HDMI Modo Hotel - Preto |
    Then sistema deve adicionar produto ao carrinho
      | TV LED Pro 43" LG 43LV300C.AWZ HD com Conversor Digital Integrado 1 USB 1 HDMI Modo Hotel - Preto |