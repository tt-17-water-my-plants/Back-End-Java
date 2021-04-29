package tt17.bw.watermyplants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableJpaAuditing
@SpringBootApplication
public class WatermyplantsApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(WatermyplantsApplication.class,
            args);
    }

}
