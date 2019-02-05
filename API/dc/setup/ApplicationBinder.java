package dc.setup;

import java.net.URL;
import java.util.Hashtable;

import javax.inject.Singleton;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.support.TransactionTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import dc.jwtutil.KeyGenerator;
import dc.jwtutil.SimpleKeyGenerator;
import dc.repository.AccountRepository;
import dc.repository.AdminRepository;
import dc.repository.FacultyRepository;
import dc.repository.StaffRepository;
import dc.repository.StudentRepository;
import dc.service.AccountService;
import dc.service.AdminService;
import dc.service.FacultyService;
import dc.service.StaffService;
import dc.service.StudentService;
import dc.utility.JDBCHelper;
import dc.utility.TokenIssuer;

public class ApplicationBinder extends AbstractBinder {

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		// Login Infrastructure
		bind(SimpleKeyGenerator.class).to(KeyGenerator.class).in(
				Singleton.class);
		bind(TokenIssuer.class).to(TokenIssuer.class).in(Singleton.class);
		// Database
		ApplicationContext jdbcac = new ClassPathXmlApplicationContext(
				"JDBCApplicationContext.xml");
		JDBCHelper helper = (JDBCHelper) jdbcac.getBean(JDBCHelper.class);
		bind(helper).to(JDBCHelper.class);
		// Object Mapper
		ObjectMapper mapper = new ObjectMapper();
		final Hashtable data = new Hashtable();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		mapper.configure(MapperFeature.USE_ANNOTATIONS, false);
		bind(mapper).to(ObjectMapper.class);
		bind(data).to(Hashtable.class);

		TransactionTemplate transactionTemplate = (TransactionTemplate) jdbcac
				.getBean(TransactionTemplate.class);
		bind(transactionTemplate).to(TransactionTemplate.class);

		// Add Service Here
		bind(AccountService.class).to(AccountService.class).to(Singleton.class);
		bind(AdminService.class).to(AdminService.class).to(Singleton.class);
		bind(FacultyService.class).to(FacultyService.class).to(Singleton.class);
		bind(StudentService.class).to(StudentService.class).to(Singleton.class);
		bind(StaffService.class).to(StaffService.class).to(Singleton.class);

		// Add Repository Here
		bind(AccountRepository.class).to(AccountRepository.class).in(
				Singleton.class);
		bind(AdminRepository.class).to(AdminRepository.class).in(
				Singleton.class);
		bind(FacultyRepository.class).to(FacultyRepository.class).in(
				Singleton.class);
		bind(StudentRepository.class).to(StudentRepository.class).in(
				Singleton.class);
		bind(StaffRepository.class).to(StaffRepository.class).in(
				Singleton.class);

	}

}
