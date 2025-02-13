## **Feature: Criação de Pedido**
**História do Usuário:**  
Como um cliente,  
Quero criar um pedido contendo itens e um endereço de entrega válido,  
Para que eu possa realizar uma compra e receber os produtos corretamente.

### ✅ **Cenário: Criar um novo pedido com itens e endereço de entrega**
**Dado** que um cliente deseja fazer um pedido  
**E** ele adiciona um ou mais itens ao pedido  
**E** ele fornece um endereço de entrega válido  
**Quando** o pedido for criado  
**Então** o pedido deve ser registrado no sistema  
**E** o status do pedido deve ser "Pendente"

### ❌ **Cenário: Falha ao criar pedido sem itens**
**Dado** que um cliente tenta criar um pedido  
**E** nenhum item foi adicionado ao pedido  
**Quando** o sistema validar a solicitação  
**Então** o sistema deve rejeitar a criação do pedido  
**E** uma mensagem de erro "Pedido deve conter pelo menos um item" deve ser exibida

### ❌ **Cenário: Falha ao criar pedido com endereço inválido**
**Dado** que um cliente tenta criar um pedido  
**E** ele fornece um endereço de entrega inválido  
**Quando** o sistema validar o endereço  
**Então** o sistema deve rejeitar a criação do pedido  
**E** uma mensagem de erro "Endereço de entrega inválido" deve ser exibida

---

## **Feature: Consulta de Pedido**
**História do Usuário:**  
Como um usuário do sistema,  
Quero buscar um pedido pelo identificador único,  
Para visualizar seus detalhes e acompanhar o status.

### ✅ **Cenário: Recuperar um pedido existente**
**Dado** que um pedido foi criado com um identificador único  
**Quando** um usuário buscar esse pedido pelo identificador  
**Então** o sistema deve retornar os detalhes do pedido  
**E** os itens do pedido devem estar listados corretamente  
**E** o endereço de entrega deve estar associado ao pedido

### ❌ **Cenário: Buscar um pedido inexistente**
**Dado** que um usuário informa um identificador de pedido que não existe  
**Quando** o sistema tentar recuperar os dados do pedido  
**Então** o sistema deve informar que o pedido não foi encontrado  
**E** uma mensagem "Pedido não encontrado" deve ser exibida

---

## **Feature: Atualização de Pedido**
**História do Usuário:**  
Como um cliente,  
Quero atualizar informações do meu pedido enquanto ele não for processado,  
Para corrigir possíveis erros ou mudanças necessárias.

### ✅ **Cenário: Atualizar o endereço de entrega de um pedido pendente**
**Dado** que um pedido está no status "Pendente"  
**E** um novo endereço de entrega válido é fornecido  
**Quando** o cliente atualizar o endereço de entrega do pedido  
**Então** o pedido deve refletir o novo endereço  
**E** a atualização deve ser registrada no histórico do pedido

### ❌ **Cenário: Tentar atualizar um pedido que já foi enviado**
**Dado** que um pedido está no status "Enviado"  
**E** um cliente tenta atualizar o endereço de entrega  
**Quando** o sistema validar a solicitação  
**Então** a atualização deve ser rejeitada  
**E** uma mensagem de erro "Pedido já processado, não pode ser alterado" deve ser exibida

---

## **Feature: Gerenciamento de Itens no Pedido**
**História do Usuário:**  
Como um cliente,  
Quero adicionar ou remover itens do meu pedido enquanto ele ainda não foi processado,  
Para garantir que estou comprando os produtos certos.

### ✅ **Cenário: Adicionar um item a um pedido pendente**
**Dado** que um pedido está no status "Pendente"  
**E** um novo item válido é selecionado  
**Quando** o cliente adicionar esse item ao pedido  
**Então** o item deve ser incluído no pedido  
**E** o total do pedido deve ser recalculado

### ❌ **Cenário: Tentar adicionar um item ao pedido, mas o produto está fora de estoque**
**Dado** que um cliente deseja adicionar um item ao pedido  
**E** o produto está fora de estoque  
**Quando** ele tentar adicionar o item  
**Então** o sistema deve rejeitar a adição  
**E** uma mensagem "Produto fora de estoque" deve ser exibida

### ✅ **Cenário: Remover um item de um pedido pendente**
**Dado** que um pedido contém um ou mais itens  
**E** o pedido ainda está no status "Pendente"  
**Quando** um cliente remover um item do pedido  
**Então** o item deve ser removido da lista de itens  
**E** o total do pedido deve ser atualizado

### ❌ **Cenário: Remover o único item do pedido**
**Dado** que um pedido contém apenas um item  
**E** o cliente tenta remover esse item  
**Quando** o sistema processar a remoção  
**Então** o sistema deve rejeitar a remoção  
**E** uma mensagem "O pedido deve conter pelo menos um item" deve ser exibida

---

## **Feature: Cancelamento de Pedido**
**História do Usuário:**  
Como um cliente,  
Quero cancelar um pedido antes que ele seja enviado,  
Para evitar a compra caso tenha mudado de ideia.

### ✅ **Cenário: Cancelar um pedido antes do processamento**
**Dado** que um pedido está no status "Pendente"  
**Quando** o cliente solicitar o cancelamento do pedido  
**Então** o pedido deve ser marcado como "Cancelado"  
**E** os itens do pedido não devem mais ser reservados

### ❌ **Cenário: Tentar cancelar um pedido que já foi enviado**
**Dado** que um pedido está no status "Enviado"  
**E** um cliente tenta cancelá-lo  
**Quando** o sistema validar a solicitação  
**Então** o cancelamento deve ser rejeitado  
**E** uma mensagem de erro "Pedido já processado, não pode ser cancelado" deve ser exibida

---

## **Feature: Conclusão de Pedido**
**História do Usuário:**  
Como um operador logístico,  
Quero marcar um pedido como concluído após sua entrega,  
Para garantir que o ciclo da compra foi finalizado corretamente.

### ✅ **Cenário: Marcar um pedido como concluído após a entrega**
**Dado** que um pedido foi entregue ao cliente  
**Quando** o sistema registrar a confirmação da entrega  
**Então** o pedido deve ser atualizado para o status "Concluído"

### ❌ **Cenário: Tentativa de concluir um pedido não entregue**
**Dado** que um pedido está no status "Enviado"  
**E** a entrega ainda não foi confirmada  
**Quando** um operador tentar marcar o pedido como "Concluído"  
**Então** o sistema deve rejeitar a ação  
**E** uma mensagem de erro "Pedido só pode ser concluído após a entrega" deve ser exibida  
