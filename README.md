# Funções para Filtrar e Agrupar Listas com Lambda

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
### Tipos
### Filtrando
Os tipos para comparar são: <b> Equals, LessThan, GreaterThan, LessThanOrEquals, GressThanOrEquals </b>

#### Equals
```java
// Vai filtrar as pessoas que tem idade igual a 19 anos
FilterBy filterEqualsAge = new FilterBy("age", ComparatorType.Equals, 19);
List<People> filteredEqualsAge = (List<People>) ArrayUtil.filterByFields(peoples, filterEqualsAge);
System.out.println("Total: " + filteredEqualsAge.size());
```

#### Less Than
```java
// Vai filtrar as pessoas que tem idade menor que 19 anos
FilterBy filterLessThanAge = new FilterBy("age", ComparatorType.LessThan, 19);
List<People> filteredLessThanAge = (List<People>) ArrayUtil.filterByFields(peoples, filterLessThanAge);
System.out.println("Total: " + filteredLessThanAge.size());
```

#### Greather Than
```java
// Vai filtrar as pessoas que tem idade maior que 19 anos
FilterBy filterGreaterThanAge = new FilterBy("age", ComparatorType.GreaterThan, 19);
List<People> filteredGreaterThanAge = (List<People>) ArrayUtil.filterByFields(peoples, filterGreaterThanAge);
System.out.println("Total: " + filteredGreaterThanAge.size());
```

#### Less Than or Equals
```java
// Vai filtrar as pessoas que tem idade menor ou igual a 19 anos
FilterBy filterLessThanOrEqualsAge = new FilterBy("age", ComparatorType.LessThanOrEquals, 19);
List<People> filteredLessThanOrEqualsAge = (List<People>) ArrayUtil.filterByFields(peoples, filterLessThanOrEqualsAge);
System.out.println("Total: " + filteredLessThanOrEqualsAge.size());
```

#### Greather Than or Equals
```java
// Vai filtrar as pessoas que tem idade maior ou igual a 19 anos
FilterBy filterGreaterThanOrEquals = new FilterBy("age", ComparatorType.GreaterThanOrEquals, 19);
List<People> filteredGreaterThanOrEquals = (List<People>) ArrayUtil.filterByFields(peoples, filterGreaterThanOrEquals);
System.out.println("Total: " + filteredGreaterThanOrEquals.size());
```
