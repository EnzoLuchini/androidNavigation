# Screen Navigation — Jetpack Compose

## Descrição do projeto

Este projeto foi desenvolvido durante as aulas de Kotlin, utilizando o Jetpack Compose para construção e estilização das telas. Além disso, implementa um sistema de navegação entre telas com o `NavController`, demonstrando na prática como é realizada a passagem de parâmetros durante a navegação.

---

## Objetivo da prova

O objetivo da prova é verificar se o aluno compreendeu os conceitos básicos do desenvolvimento Android com Jetpack Compose e navegação entre telas, pedindo que explique com suas próprias palavras como esses conceitos foram aplicados no projeto desenvolvido.

---

## Evolução dos commits

### 1 — Passagem de parâmetro obrigatório para a tela de Perfil

Neste commit, configuramos a rota do `composable` para receber um parâmetro ao abrir a tela de Perfil. Para isso, é necessário adicionar o nome do parâmetro entre chaves diretamente na rota. Isso indica ao Navigation que aquele valor faz parte do caminho e é obrigatório — a rota só será reconhecida se o parâmetro for fornecido.

Para ler o valor recebido dentro do composable, utilizamos `it.arguments?.getString("nome")`, passando o nome exato do parâmetro definido na rota. A função da tela também foi atualizada para receber essa variável:

```kotlin
composable(route = "perfil/{nome}") {
    val nome: String? = it.arguments?.getString("nome")
    PerfilScreen(
        modifier = Modifier.padding(innerPadding),
        navController = navController,
        nome!!
    )
}
```

```kotlin
fun PerfilScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    nome: String
)
```

---

### 2 — Passagem de parâmetro opcional para a tela de Pedidos

Neste commit, a rota de pedidos foi modificada para aceitar um parâmetro opcional `cliente`. A diferença em relação ao commit anterior está em dois aspectos: a forma de declarar a rota, usando query string (`"pedidos?cliente={cliente}"`), e a declaração formal do parâmetro com `navArgument`.

O `listOf(navArgument)` serve para registrar os metadados do parâmetro junto ao Navigation — neste caso, definindo um valor padrão que será utilizado quando nenhum cliente for informado na navegação. A leitura do valor em si continua sendo feita normalmente via `it.arguments?.getString("cliente")`.

```kotlin
composable(
    route = "pedidos?cliente={cliente}",
    arguments = listOf(navArgument("cliente") {
        defaultValue = "Cliente Genérico"
    })
) {
    PedidosScreen(
        modifier = Modifier.padding(innerPadding),
        navController = navController,
        it.arguments?.getString("cliente")
    )
}
```

---

### 3 — Inserindo o valor do parâmetro opcional na navegação

Neste commit, foi adicionada a passagem efetiva do parâmetro ao navegar para a tela de Pedidos. Seguindo o padrão de query string, o valor é incluído diretamente na chamada de navegação:

```kotlin
onClick = { navController.navigate("pedidos?cliente=Cliente XPTO") }
```

Caso o parâmetro seja omitido, o Navigation utiliza automaticamente o `defaultValue` definido no commit anterior.

---

### 4 — Passagem de múltiplos parâmetros

Neste commit, a tela de Perfil foi atualizada para receber dois parâmetros, adicionando mais um segmento à rota:

```kotlin
composable(
    route = "perfil/{nome}/{idade}",
    arguments = listOf(
        navArgument("nome") { type = NavType.StringType },
        navArgument("idade") { type = NavType.IntType }
    )
) {
    val nome: String? = it.arguments?.getString("nome", "Usuário Genérico")
    val idade: Int? = it.arguments?.getInt("idade", 0)
    PerfilScreen(
        modifier = Modifier.padding(innerPadding),
        navController = navController,
        nome!!,
        idade!!
    )
}
```

Como `idade` é um valor inteiro, foi necessário declarar explicitamente o tipo de cada parâmetro com `NavType`. Sem essa declaração, o Navigation trata todos os valores como `String` por padrão, o que causaria erro na conversão. As demais alterações foram na assinatura da função da tela de Perfil, que passou a receber e utilizar ambos os parâmetros:

```kotlin
fun PerfilScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    nome: String,
    idade: Int
)
```

---

## Estrutura de telas

| Rota | Parâmetros | Tipo |
|------|-----------|------|
| `login` | — | — |
| `menu` | — | — |
| `perfil/{nome}/{idade}` | `nome`, `idade` | Obrigatórios |
| `pedidos?cliente={cliente}` | `cliente` | Opcional |
