package com.repsrv.csweb.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.RegionUtils;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.cloud.aws.paramstore.AwsParamStoreProperties;
import org.springframework.cloud.aws.paramstore.AwsParamStorePropertySourceLocator;
import org.springframework.cloud.aws.secretsmanager.AwsSecretsManagerProperties;
import org.springframework.cloud.aws.secretsmanager.AwsSecretsManagerPropertySourceLocator;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.Properties;
import java.util.logging.Logger;

public class AwsConfigParamsPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
private static final Logger logger = Logger.getLogger(AwsConfigParamsPlaceholderConfigurer.class.getName());

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException
    {
        //this.localOverride = true;
        String configPrefix = beanFactory.getBean("configPrefix", String.class);

        String strRegion = beanFactory.getBean("awsRegion", String.class);
        Region awsRegion = Region.getRegion(Regions.fromName(strRegion));
        
        logger.info("Region for ssm: " + strRegion);
        logger.info("configPrefix: " + configPrefix);

        DefaultAWSCredentialsProviderChain awsCredentialsProvider = beanFactory
                .getBean("awsCredentialsProvider", DefaultAWSCredentialsProviderChain.class);

        AWSSimpleSystemsManagementClient ssmClient = beanFactory.getBean("ssmClient", AWSSimpleSystemsManagementClient.class);
        ssmClient.setRegion(awsRegion);

        AwsParamStoreProperties awsParamStoreProperties = new AwsParamStoreProperties();
        awsParamStoreProperties.setDefaultContext(configPrefix);
        awsParamStoreProperties.setName(configPrefix);

        AwsParamStorePropertySourceLocator awsParameterStorePropertySource = new AwsParamStorePropertySourceLocator(ssmClient, awsParamStoreProperties);

        AwsSecretsManagerProperties awsSecretsManagerProperties = new AwsSecretsManagerProperties();
        awsSecretsManagerProperties.setDefaultContext(configPrefix);
        awsSecretsManagerProperties.setName(configPrefix);

        AWSSecretsManager secretsManagerClient = AWSSecretsManagerClientBuilder.standard()
                //.withCredentials(awsCredentialsProvider)
                .withRegion(awsRegion.getName())
                .build();

        AwsSecretsManagerPropertySourceLocator awsSecretsManagerPropertySource = new AwsSecretsManagerPropertySourceLocator(secretsManagerClient, awsSecretsManagerProperties);

        ConfigurableEnvironment environment = beanFactory.getBean(ConfigurableEnvironment.class);
        MutablePropertySources propertySources = environment.getPropertySources();

        // Add the AWS Property Sources to the environment

        propertySources.addFirst(awsSecretsManagerPropertySource.locate(environment));
        propertySources.addFirst(awsParameterStorePropertySource.locate(environment));

        final Properties props = new Properties();

        propertySources.forEach(p-> {
                if (p instanceof EnumerablePropertySource) {
                    EnumerablePropertySource<?> enumerablePropertySource = (EnumerablePropertySource<?>) p;
                    for (String propertyName : enumerablePropertySource.getPropertyNames()) {
                       props.put(propertyName, environment.getProperty(propertyName));
                    }
                }
        });

       setProperties(props);

    System.out.println("!!!! Config loaded  !!!! prefix:" + configPrefix +"  region:" + awsRegion.getName());
       // printPropertySources(beanFactory);
       super.postProcessBeanFactory(beanFactory);
    }

    private void printPropertySources(ConfigurableListableBeanFactory beanFactory) {
        ConfigurableEnvironment environment = beanFactory.getBean(ConfigurableEnvironment.class);
        MutablePropertySources propertySources = environment.getPropertySources();
        propertySources.forEach(p->{
            System.out.println("Property Source: " + p.getName());
            if (p instanceof EnumerablePropertySource) {
                EnumerablePropertySource<?> enumerablePropertySource = (EnumerablePropertySource<?>) p;
                for (String propertyName : enumerablePropertySource.getPropertyNames()) {
                    System.out.println(propertyName + ": " + environment.getProperty(propertyName));
                }
            }
        });
    }
}
