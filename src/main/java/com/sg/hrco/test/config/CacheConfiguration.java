package com.sg.hrco.test.config;

import io.github.jhipster.config.JHipsterProperties;
import java.time.Duration;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration =
            Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder
                    .newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                    .build()
            );
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.sg.hrco.test.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.sg.hrco.test.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.sg.hrco.test.domain.User.class.getName());
            createCache(cm, com.sg.hrco.test.domain.Authority.class.getName());
            createCache(cm, com.sg.hrco.test.domain.User.class.getName() + ".authorities");
            createCache(cm, com.sg.hrco.test.domain.WaEmployee.class.getName());
            createCache(cm, com.sg.hrco.test.domain.WaEmployee.class.getName() + ".employees");
            createCache(cm, com.sg.hrco.test.domain.WaEmployee.class.getName() + ".genders");
            createCache(cm, com.sg.hrco.test.domain.WaEmployee.class.getName() + ".nationalities");
            createCache(cm, com.sg.hrco.test.domain.WaEmployee.class.getName() + ".maritalStatuses");
            createCache(cm, com.sg.hrco.test.domain.WaEmployee.class.getName() + ".personalAddresses");
            createCache(cm, com.sg.hrco.test.domain.WaEmployee.class.getName() + ".assignments");
            createCache(cm, com.sg.hrco.test.domain.WaEmployee.class.getName() + ".hostCompanies");
            createCache(cm, com.sg.hrco.test.domain.WaEmployee.class.getName() + ".homeCompanies");
            createCache(cm, com.sg.hrco.test.domain.WaEmployee.class.getName() + ".jobs");
            createCache(cm, com.sg.hrco.test.domain.WaEmployee.class.getName() + ".classifications");
            createCache(cm, com.sg.hrco.test.domain.WaEmployee.class.getName() + ".managementHrises");
            createCache(cm, com.sg.hrco.test.domain.WaEmployee.class.getName() + ".managerialLinks");
            createCache(cm, com.sg.hrco.test.domain.WaEmployee.class.getName() + ".seniorities");
            createCache(cm, com.sg.hrco.test.domain.WaEmployee.class.getName() + ".languages");
            createCache(cm, com.sg.hrco.test.domain.WaEmployee.class.getName() + ".fixedCompensations");
            createCache(cm, com.sg.hrco.test.domain.WaEmployee.class.getName() + ".variableCompensations");
            createCache(cm, com.sg.hrco.test.domain.WaGender.class.getName());
            createCache(cm, com.sg.hrco.test.domain.WaNationality.class.getName());
            createCache(cm, com.sg.hrco.test.domain.WaMaritalStatus.class.getName());
            createCache(cm, com.sg.hrco.test.domain.WaPersonalAddress.class.getName());
            createCache(cm, com.sg.hrco.test.domain.WaAssignment.class.getName());
            createCache(cm, com.sg.hrco.test.domain.WaHostCompany.class.getName());
            createCache(cm, com.sg.hrco.test.domain.WaHomeCompany.class.getName());
            createCache(cm, com.sg.hrco.test.domain.WaJob.class.getName());
            createCache(cm, com.sg.hrco.test.domain.WaClassification.class.getName());
            createCache(cm, com.sg.hrco.test.domain.WaManagementHris.class.getName());
            createCache(cm, com.sg.hrco.test.domain.ManagerialLink.class.getName());
            createCache(cm, com.sg.hrco.test.domain.WaSeniority.class.getName());
            createCache(cm, com.sg.hrco.test.domain.WaLanguage.class.getName());
            createCache(cm, com.sg.hrco.test.domain.FixedCompensation.class.getName());
            createCache(cm, com.sg.hrco.test.domain.VariableCompensation.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }
}
