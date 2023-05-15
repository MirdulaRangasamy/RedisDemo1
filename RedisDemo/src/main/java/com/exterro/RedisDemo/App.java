package com.exterro.RedisDemo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

/**
 * Hello world!
 *
 */
public class App {
	private static final String redisHost = "127.0.0.1";
	private static final Integer redisPort = 6379;

	private static JedisPool pool = new JedisPool(redisHost, redisPort);
	public static void main( String[] args )
    {
		//Jedis jedis = pool.getResource();
		//jedis.set("tech", "Java");
		//System.out.println("done...");
		//System.out.println(jedis.get("tech"));
		
		
		//addSets();
	addHash();
		
    }
	

	@SuppressWarnings("deprecation")
    	public static void addSets() {
    	// let us first add some data in our redis server using Redis SET.
    	String key = "Freshers2021";
    	String member1 = "Karthik";
    	String member2 = "Bala";
    	String member3 = "SivaPrakash";
    	String member4 = "Deepan";
    	String member5 = "Brucelee";
    	String member6 = "Anto";

    	// get a jedis connection jedis connection pool
    	Jedis jedis = pool.getResource();
    	try {
    	// save to redis
    	jedis.sadd(key, member1, member2, member3, member4,member5,member6);

    	// after saving the data, lets retrieve them to be sure that it has
    	// really added in redis
    	Set<String> members = jedis.smembers(key);
    	for (String member : members) {
    	System.out.println(member);
    	}
    	} catch (JedisException e) {
    	// if something wrong happen, return it back to the pool
    	if (jedis != null) {
    	pool.returnBrokenResource(jedis);
    	jedis = null;
    	}
    	} finally {
    	/// it's important to return the Jedis instance to the pool once
    	/// you've finished using it
    	if (jedis != null)
    	pool.returnResource(jedis);
    	}
	}
	public static void addHash() {
		// add some values in Redis HASH
		String key = "Freshers2023Apr";
		Map<String, String> map = new HashMap();
		map.put("name", "Nandhini");
		map.put("domain", "Hibernate");
		map.put("description", "Good");
		Jedis jedis = pool.getResource();
		try {
			// save to redis
			jedis.hmset(key, map);

			// after saving the data, lets retrieve them to be sure that it has
			// really added in redis
			Map<String, String> retrieveMap = jedis.hgetAll(key);
			for (String keyMap : retrieveMap.keySet()) {
				System.out.println(keyMap + " " + retrieveMap.get(keyMap));
			}

		} catch (JedisException e) {
			// if something wrong happen, return it back to the pool
			if (jedis != null) {
				pool.returnBrokenResource(jedis);
				jedis = null;
			}
		} finally {
			/// it's important to return the Jedis instance to the pool once
			/// you've finished using it
			if (jedis != null)
				pool.returnResource(jedis);
		}
	}
}
