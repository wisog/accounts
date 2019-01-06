package com.stokkur.exam.demoAccounts;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static com.stokkur.exam.demoAccounts.rest.util.ApiConstant.API_PATH;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class DemoAccountsApplicationTests {

	@LocalServerPort
	private int port; //if the server's port change

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TestRestTemplate restTemplate;

	private String getAccountsUrl(){
		return "http://localhost:" +
				port + API_PATH + "/accounts/";
	}

	private String newAccountJSON = "{\n" +
			"    \"username\": \"user2\",\n" +
			"    \"email\": \"mail@stokkur.com\",\n" +
			"    \"password\": \"secret\"\n" +
			"}";


	/**
	 * Test for the GET request over /accounts/ entrypoint
	 * @throws Exception
	 */
	@Test
	public void getAccount() throws Exception{
		assertThat(this.restTemplate.getForObject(getAccountsUrl(), String.class)).contains(
						"[{\"id\":0,\"username\":\"user1\",\"email\":\"test@test.com\"");
	}

	/**
	 * Test for the POST request without credentials over an
	 * authenticated entrypoint
	 * @throws Exception
	 */
	@Test
	public void createAccountWithoutCredentials() throws Exception{
		this.mockMvc.perform( post(getAccountsUrl()) ).andDo(print()).andExpect(status().isUnauthorized());
	}

	/**
	 * Test for the POST request using credentials in order to create
	 * an account entity
	 * @throws Exception
	 */
	@Test
	@WithMockUser(username = "root", password = "toor", roles = "ADMIN")
	public void createAccountWithMockUser() throws Exception {
		this.mockMvc.perform( post(getAccountsUrl())
		.contentType(MediaType.APPLICATION_JSON)
		.content(newAccountJSON)
		).andDo(print()).andExpect(status().isOk());
	}

	/**
	 * Test for the POST request using credentials and duplicate date
	 * to check BAD_REQUEST response and error message
	 * @throws Exception
	 */
	@Test
	@WithMockUser(username = "root", password = "toor", roles = "ADMIN")
	public void createSameAccountTwiceWithMockUser() throws Exception {
		this.mockMvc.perform( post(getAccountsUrl())
				.contentType(MediaType.APPLICATION_JSON)
				.content(newAccountJSON)
		).andDo(print()).andExpect(
				status().isBadRequest())
				.andExpect(content().string("Duplicated Entry"));
	}
}

