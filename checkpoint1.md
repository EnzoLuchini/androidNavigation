# Screen Navigation — Jetpack Compose

## Descrição do projeto

Este projeto foi desenvolvido durante as aulas de Kotlin, utilizando o Jetpack Compose para construção e estilização das telas. Além disso, implementa um sistema de navegação entre telas com o `NavController`, demonstrando na prática como é realizada a passagem de parâmetros durante a navegação.

---

## Objetivo da prova

O objetivo da prova é verificar se o aluno compreendeu os conceitos básicos do desenvolvimento Android com Jetpack Compose e navegação entre telas, pedindo que explique com suas próprias palavras como esses conceitos foram aplicados no projeto desenvolvido.

---

## Evolução dos commits

### 1 — Passagem de parâmetro obrigatório para a tela de Perfil

Neste commit, configuramos a rota do `composable` para receber um parâmetro ao abrir a tela de Perfil. Para isso, adicionamos o nome do parâmetro entre chaves diretamente na rota: `"perfil/{nome}"`. Essa sintaxe funciona como um template — quando o `navController.navigate("perfil/João")` é chamado, o Navigation faz o match posicional entre a URL e o template da rota, extrai o valor `"João"` e o injeta automaticamente no `Bundle` da entrada de navegação. Por ser parte do caminho, o parâmetro é obrigatório: navegar para `"perfil/"` sem o valor faz com que a rota não seja reconhecida.

Para ler o valor dentro do composable, utilizamos `it.arguments?.getString("nome")`, onde `it` é o `NavBackStackEntry` — o objeto que o Navigation entrega com todas as informações da navegação atual, incluindo o Bundle populado. A função da tela também foi atualizada para receber essa variável:

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

Neste commit, a rota de pedidos foi modificada para aceitar um parâmetro opcional `cliente`. A principal diferença em relação ao commit anterior está na forma de declarar a rota: em vez de usar o segmento de path `{cliente}`, utilizamos a convenção de query string `"pedidos?cliente={cliente}"`. O motivo é que `"pedidos"` sem nenhum parâmetro ainda é uma URL válida e reconhecida pelo Navigation — diferente do path, onde a ausência do segmento quebraria o match da rota completamente.

Para que o parâmetro opcional funcione corretamente, é necessário declarar seus metadados com `navArgument` dentro de um `listOf`. É ali que definimos o `defaultValue`, que será usado automaticamente pelo Navigation quando nenhum valor for passado na navegação. A leitura do valor em si continua sendo feita da mesma forma via `it.arguments?.getString("cliente")`.

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

Com múltiplos segmentos no path, a ordem importa. O Navigation faz o match posicional — o primeiro valor sempre vai para `nome` e o segundo para `idade`, independentemente do conteúdo. Se a chamada `navController.navigate("perfil/25/João")` for feita com os valores invertidos, os dados chegam errados sem nenhum erro em tempo de compilação, tornando esse tipo de bug difícil de identificar.

Como `idade` é um valor inteiro, foi necessário declarar explicitamente o tipo de cada parâmetro com `NavType`. Sem essa declaração, o Navigation trata todos os valores como `String` por padrão — o que causaria uma exceção em tempo de execução ao tentar ler `idade` com `getInt`. As demais alterações foram na assinatura da função da tela de Perfil, que passou a receber e utilizar ambos os parâmetros:

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
