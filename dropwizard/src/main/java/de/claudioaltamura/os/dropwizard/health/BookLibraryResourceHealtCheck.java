package de.claudioaltamura.os.dropwizard.health;

import com.codahale.metrics.health.HealthCheck;

public class BookLibraryResourceHealtCheck extends HealthCheck {

	@Override
	protected Result check() throws Exception {
        return Result.healthy();
	}
	
}