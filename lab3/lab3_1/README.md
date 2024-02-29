# Lab 3.1 - Review questions about Employee manager test
___
## a) Identify a couple of examples that use AssertJ expressive methods chaining.

### A_EmployeeRepositoryTest class

```
assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());
```

### B_EmployeeService_UnitTest class

```
assertThat(allEmployees).hasSize(3).extracting(Employee::getName).contains(alex.getName(), john.getName(), bob.getName());
```


### D_EmployeeRestControllerIT class

```
assertThat(found).extracting(Employee::getName).containsOnly("bob");
```

___


## b) Identify an example in which you mock the behavior of the repository (and avoid involving a database).

### B_EmployeeService_UnitTest class

```
    ...
  @Mock( lenient = true)
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void setUp() {

        //these expectations provide an alternative to the use of the repository
        Employee john = new Employee("john", "john@deti.com");
        john.setId(111L);

        Employee bob = new Employee("bob", "bob@deti.com");
        Employee alex = new Employee("alex", "alex@deti.com");

        List<Employee> allEmployees = Arrays.asList(john, bob, alex);

        Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john);
        Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);
        Mockito.when(employeeRepository.findByName("wrong_name")).thenReturn(null);
        Mockito.when(employeeRepository.findById(john.getId())).thenReturn(Optional.of(john));
        Mockito.when(employeeRepository.findAll()).thenReturn(allEmployees);
        Mockito.when(employeeRepository.findById(-99L)).thenReturn(Optional.empty());
    }
    ...
```

___

## c) What is the difference between standard @Mock and @MockBean?

### @Mock -> an annotation that works as a shorthand for the Mockito.mock() method, works only with test' classes that have Mockito annotaitons enabled (@RunWith(MockitoJUnitRunner.class))

### @MockBean -> an annotation that adds mock object to a spring app context (@RunWith(SpringRunner.class)), this object annotated will be replaced by the mock instance
### it's farly useful in integration tests, when we need a particular external service needs to be mocked

___
## d) What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?

### The ```application-integrationtest.properties``` file has the role of a  as a normal ``` .propertios``` file, in this project with properties variables for the database jpa access credentials, his is used in the context of testing, since we can use it to over
### override the default ```application.properties``` of the project for testing purpose, like in context when we are still in testing/development phase of the project/code 

#### For example:
```
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ExampleApplication.class)
@TestPropertySource(locations="classpath:application-integrationtest.properties")
public class ExampleApplicationTests {

}
```
___


## e) the sample project demonstrates three test strategies to assess an API (C, D and E) developed with SpringBoot. Which are the main/key differences?[D_EmployeeRestControllerIT.java](gs-employee-mngr%2Fsrc%2Ftest%2Fjava%2Ftqsdemo%2Femployeemngr%2Femployee%2FD_EmployeeRestControllerIT.java)

C -> uses @MockBean mock object injection for MockMvc class (non mock extend class)
D -> boudering the the context of the aplication a web framework env for only (which is a Mock environment itself) and only the designated application classes
E -> Doesnt use any mock object or environment, its a integrating test that properly access the api and even using a random selected server port     @LocalServerPort 