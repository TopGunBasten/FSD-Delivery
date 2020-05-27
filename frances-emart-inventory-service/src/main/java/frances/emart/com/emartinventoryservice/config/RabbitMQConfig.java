package frances.emart.com.emartinventoryservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class RabbitMQConfig {
    @Value("${emart.rabbitmq.queue}")
	String queueName;

	@Value("${emart.rabbitmq.exchange}")
	String exchange;

	@Value("${emart.rabbitmq.routingkey}")
	private String routingkey;

	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}

	@Bean
	DirectExchange exchange() {
		return new DirectExchange(exchange);
	}

	@Bean
	Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routingkey);
	}

	
	// // @Bean
	// // public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
	// // 	final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
	// // 	rabbitTemplate.setMessageConverter(jsonMessageConverter());
	// // 	return rabbitTemplate;
	// }
}