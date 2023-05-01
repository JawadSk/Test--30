# Test-30
>## Frameworks
 * ![SpringBoot](https://img.shields.io/badge/SpringBoot-White?style=flat&logoColor=Blue)
>## Language Used
* ![Java](https://img.shields.io/badge/Java-White?style=flat&logoColor=Blue)

## Model
### Job Properties
* @Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
 private Long id;

* @NotEmpty
private String title;

* private String description;

* @NotEmpty
private String location;

* private Double salary;

* @NotEmpty
private String companyName;

* private String employerName;

* @Enumerated(value = EnumType.STRING)
private JobType jobType;

* private LocalDate appliedDate;

## Job Type Ennum

* public enum JobType {
  IT,
  HR,
  SALES,
  MARKETING,
  ACCOUNTANT,
  PLANNER
}

>## Data Flow
### End Points/Controller

* Using CrudRepository Methods :
@PostMapping(value = "jobs")
@GetMapping(value = "jobs")
@PutMapping(value = "job")
@DeleteMapping(value = "job/{id}")
* Using Custom Finder Methods :
@GetMapping(value = "jobs/{salary}")
@GetMapping(value = "jobs/salary/greater/{salary}/sort/desc/appliedDate")
@GetMapping(value = "/jobs/description/contain/{str}")
@GetMapping(value = "jobs/jobType/not/{myType}")
* Using Native Query Methods :
@PutMapping(value = "jobs/location/{location}/id/{id}")
@DeleteMapping(value = "job/native/{id}")
@GetMapping(value = "jobs/title/{title}")
@GetMapping(value = "jobs/description/{description}")

### Services

* Using CrudRepository Methods : 
public String addJobsToDb(List<Job> jobs)
 public List<Job> getJobsFromDb()
 public String updateJobById(Job updatedJob)
 public String deleteJobByIdFromDb(Long id)
 
 * Using Custom Finder Methods : 
 public List<Job> getJobBySalaryFromDb(Double salary)
 @Transactional
public String deleteByIdNative(Long id)
 public List<Job> getAllJobsByTitle(String title)
 public List<Job> getAllJobsByDescription(String description)

### Repository

@Repository
public interface IJobRepository extends CrudRepository<Job, Long> {

  // Custom Finder Methods -->>
  public List<Job> findBySalary(Double salary);
  public List<Job> findBySalaryGreaterThanOrderByAppliedDateDesc(Double salary);
  public List<Job> findByDescriptionContains(String str);
  public List<Job> findByJobTypeNot(JobType jobType);

  // Native Custom Query -->>
 * @Modifying
  @Query(value = "update jobs set location = :location where id = :id", nativeQuery = true)
  public void updateLocationById(Long id, String location);
  @Modifying
  @Query(value = "delete from jobs where id = :id", nativeQuery = true)
  public void deleteJobById(Long id);
  @Query(value = "select * from jobs where title = :title", nativeQuery = true)
  public List<Job> selectJobByTitle(String title);
  @Query(value = "select * from jobs where description = :description", nativeQuery = true)
  public List<Job> selectJobByDescription(String description);
}

### DataBase

* In this project for datasource I've used H2 Database's in memory type with SpringDataJPA.

## DataStructures

* List<>/Inumrable<>

>## Project Summary
* This API is a Spring Boot project that is about managing jobs. We can create, read, update, and delete jobs. In this
project request is sent from the client on HTTP in JSON format or from path variable with server side validations and
stored in object then response is sent back from the server by JSON format to the client.
