package in.nareshit.raghu.model;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usertab")
public class User {
	@Id
	@GeneratedValue
	@Column(name="uid")
	private Integer usrId;
	
	@Column(name="uname")
	private String usrName;
	@Column(name="uemail")
	private String usrMail;
	@Column(name="upwd")
	private String usrPwd;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="rolestab",
	joinColumns = @JoinColumn(name="uid"))
	@Column(name="urole")
	private Set<String> usrRoles;
}
