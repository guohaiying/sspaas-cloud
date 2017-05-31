package test;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import com.sspaas.sspaascloud.dao.appmng.capacity.CapacityMapper;
import com.sspaas.sspaascloud.dao.appmng.user.UserCapacityMapper;
import com.sspaas.sspaascloud.dao.appmng.user.UserMapper;
import com.sspaas.sspaascloud.dao.appmng.user.UserPurchaseCapacityMapper;
import com.sspaas.sspaascloud.entity.appmng.UserCapacity;
import com.sspaas.sspaascloud.entity.appmng.UserPurchaseCapacity;
import com.sspaas.sspaascloud.service.appmng.redis.IRedisKit;
import com.sspaas.sspaascloud.service.appmng.redis.colony.IColonyRedis;
import com.sspaas.sspaascloud.service.appmng.user.IUserService;

import redis.clients.jedis.JedisCluster;

/**
 * Unit test for simple App.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-context.xml","/spring-mybatis.xml"})
@Transactional
//@ContextConfiguration({"/spring-mybatis.xml"})
public class AppTest {

	@Autowired
	private IRedisKit redis;
	
	@Autowired
	private IColonyRedis coredis;

	@Autowired
	private IUserService user;
	
	@Autowired
	private JedisCluster jedisCluster;

	@Autowired
	private UserMapper uesrDAO;
	
	@Autowired
	private CapacityMapper capacityDAO;
	
	@Autowired
	private UserPurchaseCapacityMapper userPurchaseCapacityDAO;
	
	@Autowired
	private UserCapacityMapper userCapacityDAO;
	
	 @Autowired
	 private DataSourceTransactionManager txManager;
	
	@Test
	public void test() throws IOException{
		boolean bool = coredis.putStrTime("ghyceshiceshi", "200", 60*1000*60);
		System.out.println(bool);
		String infaf = coredis.getStrByKey("ghyceshiceshi");
		System.out.println(infaf);
		/*
			DefaultTransactionDefinition def = new DefaultTransactionDefinition();
			def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);// 事物隔离级别，开启新事务
			TransactionStatus status = txManager.getTransaction(def); // 获得事务状态
			
		try {
			Map<String, Object> capacityMap = capacityDAO.selectIdByType();
			UserPurchaseCapacity userPurchaseCapacity = new UserPurchaseCapacity();
			userPurchaseCapacity.setAddTime(new Date().getTime());
			userPurchaseCapacity.setUserId(111111111);
			userPurchaseCapacity.setCapacityId((Integer)capacityMap.get("capacityId"));
			userPurchaseCapacity.setPrice((double)0);
			userPurchaseCapacity.setState((short)1); //1正常  2到期
			userPurchaseCapacityDAO.addUserPurchaseCapacity(userPurchaseCapacity);
			
			//初始化用户总容量
			UserCapacity userCapacity = new UserCapacity();
			userCapacity.setTotalCapacity((Long)capacityMap.get("size")); //总容量
			userCapacity.setUserdCapacity((long)0);
			userCapacity.setUserId(111111111);
			userCapacityDAO.addUserCapacity(userCapacity);
			txManager.commit(status);
			int a = 5/0;
		} catch (Exception e) {
			txManager.rollback(status);
		}*/
	}

	
	public static void main(String[] args) {
		System.out.println(111);
	}

}
