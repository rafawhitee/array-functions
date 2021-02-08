# Array Functions 
Funções para Filtrar e Agrupar Listas com Lambda

# Usage
Classe pessoa utilizada para os testes e exemplo:

<b> Classe People </b>
```java
private int id;
private String name;
private int age;
private double weight;
private double height;
private boolean active;
private Profile profile;
```

<b> Classe Profile </b>
```java
private Integer id;
private String description;
```

## Filtrando
Os tipos para comparar são: <b> Equals, LessThan, GreaterThan, LessThanOrEquals, GressThanOrEquals </b>

#### Equals
```java
// Vai filtrar as pessoas que tem idade igual a 19 anos
FilterBy filterEqualsAge = new FilterBy("age", ComparatorType.Equals, 19);
List<People> filteredEqualsAge = (List<People>) ArrayUtil.filterByFields(peoples, filterEqualsAge);
System.out.println("Total Equals Age: " + filteredEqualsAge.size());
```

#### Less Than
```java
// Vai filtrar as pessoas que tem idade menor que 19 anos
FilterBy filterLessThanAge = new FilterBy("age", ComparatorType.LessThan, 19);
List<People> filteredLessThanAge = (List<People>) ArrayUtil.filterByFields(peoples, filterLessThanAge);
System.out.println("Total LessThan Age: " + filteredLessThanAge.size());
```

#### Greater Than
```java
// Vai filtrar as pessoas que tem idade maior que 19 anos
FilterBy filterGreaterThanAge = new FilterBy("age", ComparatorType.GreaterThan, 19);
List<People> filteredGreaterThanAge = (List<People>) ArrayUtil.filterByFields(peoples, filterGreaterThanAge);
System.out.println("Total GreaterThan Age: " + filteredGreaterThanAge.size());
```

#### Less Than or Equals
```java
// Vai filtrar as pessoas que tem idade menor ou igual a 19 anos
FilterBy filterLessThanOrEqualsAge = new FilterBy("age", ComparatorType.LessThanOrEquals, 19);
List<People> filteredLessThanOrEqualsAge = (List<People>) ArrayUtil.filterByFields(peoples, filterLessThanOrEqualsAge);
System.out.println("Total LessThanOrEquals Age: " + filteredLessThanOrEqualsAge.size());
```

#### Greather Than or Equals
```java
// Vai filtrar as pessoas que tem idade maior ou igual a 19 anos
FilterBy filterGreaterThanOrEquals = new FilterBy("age", ComparatorType.GreaterThanOrEquals, 19);
List<People> filteredGreaterThanOrEquals = (List<People>) ArrayUtil.filterByFields(peoples, filterGreaterThanOrEquals);
System.out.println("Total GreaterThanOrEquals Age: " + filteredGreaterThanOrEquals.size());
```

#### Filtrando com Atributos de Relacionamento
Você pode colocar no parâmetro do atributo, nome de atributos que são relacionamentos entre classe também, como por exemplo a classe People tem declarado um Profile com ID e Description, eu quero filtrar pela description, ficando: <b> profile.description </b>. Similiar aos Root que a Criteria JPA utiliza para fazer queries dinâmicas.
<b> Exemplos: </b>
```java
// Filtrando por profile.description === "ADMINISTRADOR"
FilterBy filterWithHierarchy = new FilterBy("profile.description", ComparatorType.Equals, ProfileType.Administrador.toString());
List<People> filteredByPerfilDescriptionAdmin = (List<People>) ArrayUtil.filterByFields(peoples, filterWithHierarchy);
System.out.println("Total Equals Perfil.Description ADMIN: " + filteredByPerfilDescriptionAdmin.size());
			
// Filtrando por profile.description === "CLIENTE"
filterWithHierarchy = new FilterBy("profile.description", ComparatorType.Equals, ProfileType.Cliente.toString());
List<People> filteredByPerfilDescriptionCliente = (List<People>) ArrayUtil.filterByFields(peoples, filterWithHierarchy);
System.out.println("Total Equals Perfil.Description CLIENTE: " + filteredByPerfilDescriptionCliente.size());
		
// Filtrando por profile.description === "GERENTE"
filterWithHierarchy = new FilterBy("profile.description", ComparatorType.Equals, ProfileType.Gerente.toString());
List<People> filteredByPerfilDescriptionGerente = (List<People>) ArrayUtil.filterByFields(peoples, filterWithHierarchy);
System.out.println("Total Equals Perfil.Description GERENTE: " + filteredByPerfilDescriptionGerente.size());
			
// Filtrando por profile.description === "VENDEDOR"
filterWithHierarchy = new FilterBy("profile.description", ComparatorType.Equals, ProfileType.Vendedor.toString());
List<People> filteredByPerfilDescriptionVendedor = (List<People>) ArrayUtil.filterByFields(peoples, filterWithHierarchy);
System.out.println("Total Equals Perfil.Description VENDEDOR: " + filteredByPerfilDescriptionVendedor.size());
```

# LICENSE
[MIT](https://github.com/rafawhitee/array-functions/blob/master/LICENSE.txt)
