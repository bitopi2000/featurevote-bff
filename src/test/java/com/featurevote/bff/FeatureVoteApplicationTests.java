package com.featurevote.bff;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class FeatureVoteApplicationTests extends AbstractIntegrationTest {

	@Test
	void contextLoads() {
	}

}
