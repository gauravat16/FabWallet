package com.fab.wallet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fab.wallet.bean.TransactionRequest;
import com.fab.wallet.bean.UserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class FabwalletApplicationTests {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mvc;

	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void canCreateUser() throws Exception {
		UserRequest request = new UserRequest();
		String name = Math.random() + "test";
		request.setUserId(name + "@starwars.com");
		request.setFname("test");
		request.setlName("Man");
		request.setPassword("password");

		MvcResult result = mvc.perform(post("/wallet/api/v1/user/add").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(asJsonString(request))).andExpect(status().isOk()).andReturn();

	}

	@Test
	public void getUserDetails() throws Exception {
		MvcResult result = mvc.perform(post("/wallet/api/v1/user/login")).andExpect(status().isOk()).andReturn();

	}

	@Test
	@WithMockUser(username = "han@starwars.com", password = "password", roles = "USER")
	public void loginTest() throws Exception {
		MvcResult result = mvc.perform(post("/wallet/api/v1/user/login")).andExpect(status().isOk()).andReturn();

	}

	@Test
	@WithMockUser(username = "han@starwars.com", password = "password", roles = "USER")
	public void updateUserDetails() throws Exception {

		UserRequest request = new UserRequest();
		request.setFname("hannibal");
		request.setlName("Smith");

		MvcResult result = mvc.perform(post("/wallet/api/v1/user/update").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(asJsonString(request))).andExpect(status().isOk()).andReturn();
	}

	@Test
	@WithMockUser(username = "han@starwars.com", password = "password", roles = "USER")
	public void getWalletDetails() throws Exception {
		MvcResult result = mvc.perform(post("/wallet/api/v1/wallet/get/details")).andExpect(status().isOk())
				.andReturn();
	}

	@Test
	@WithMockUser(username = "han@starwars.com", password = "password", roles = "USER")
	public void doCreditTransaction() throws Exception {

		TransactionRequest request = new TransactionRequest();
		request.setAmount(33.45);

		MvcResult result = mvc.perform(post("/wallet/api/v1/wallet/txn/credit")
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(asJsonString(request))).andExpect(status().isOk())
				.andReturn();
	}

	@Test
	@WithMockUser(username = "han@starwars.com", password = "password", roles = "USER")
	public void doDebitTransaction() throws Exception {

		TransactionRequest request = new TransactionRequest();
		request.setAmount(33.45);

		MvcResult result = mvc.perform(post("/wallet/api/v1/wallet/txn/debit")
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(asJsonString(request))).andExpect(status().isOk())
				.andReturn();
	}

	/**
	 * Converts Object to String.
	 * 
	 * @param obj
	 * @return
	 */
	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
