Feature: Criar Cliente

  Scenario: Criar um novo cliente com dados válidos
    Given um cliente válido
    When o cliente é salvo
    Then o cliente deve ser salvo com sucesso