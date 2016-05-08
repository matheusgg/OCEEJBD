package br.com.ejb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import static br.com.ejb.model.Employee.SELECT_ALL_NAMED_NATIVE_QUERY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedNativeQuery(
		name = SELECT_ALL_NAMED_NATIVE_QUERY,
		query = "select e.*, t.* " +
				"from Employee e " +
				"inner join Employee_Task et on e.id = et.Employee_id " +
				"inner join Task t on et.tasks_id = t.id",
		resultSetMapping = "Employee_Tasks_Mapping")
@SqlResultSetMapping(name = "Employee_Tasks_Mapping",
		entities = {
				@EntityResult(entityClass = Employee.class),
				//				@EntityResult(entityClass = Task.class, fields = {
				//						@FieldResult(name = "id", column = "t_id"),
				//						@FieldResult(name = "name", column = "t_name")
				//				})
		})
public class Employee implements Serializable {

	private static final long serialVersionUID = -8654188442146018297L;
	public static final String SELECT_ALL_NAMED_NATIVE_QUERY = "Employee.selectAll";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;

	@Temporal(TemporalType.DATE)
	private Date hireDate;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Task> tasks;

}
