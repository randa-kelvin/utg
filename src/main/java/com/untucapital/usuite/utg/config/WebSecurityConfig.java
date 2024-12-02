package com.untucapital.usuite.utg.config;

import com.untucapital.usuite.utg.auth.AuthEntryPoint;
import com.untucapital.usuite.utg.auth.AuthFilter;
import com.untucapital.usuite.utg.auth.UserPrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Chirinda Nyasha Dell 22/11/2021
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserPrincipalService userPrincipalService;
    private final AuthEntryPoint authEntryPoint;

    @Autowired
    public WebSecurityConfig(UserPrincipalService userPrincipalService, AuthEntryPoint authEntryPoint) {
        this.userPrincipalService = userPrincipalService;
        this.authEntryPoint = authEntryPoint;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userPrincipalService);
        return provider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthFilter authFilter() {
        return new AuthFilter();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js")
                .permitAll()
                .antMatchers("/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/webjars/**")
                .permitAll()
                .antMatchers("/auth/**")
                .permitAll()
                .antMatchers("/direct_cost/**")
                .permitAll()
                .antMatchers("/period_of_analysis/**")
                .permitAll()
                .antMatchers("/stock/**")
                .permitAll()
                .antMatchers("/operational_cost/**")
                .permitAll()
                .antMatchers("/users/**")
                .permitAll()
                .antMatchers("/credit_application/**")
                .permitAll()
                .antMatchers("/credit_app/**")
                .permitAll()
                .antMatchers("/cms_authorisation/**")
                .permitAll()
                .antMatchers("/industries/**")
                .permitAll()
                .antMatchers("/uploadFile/**")
                .permitAll()
                .antMatchers("/uploadMultipleFiles/**")
                .permitAll()
                .antMatchers("/downloadFile/**")
                .permitAll()
                .antMatchers("/downloadFiles/**")
                .permitAll()
                .antMatchers("/business/**")
                .permitAll()
                .antMatchers("/cities/**")
                .permitAll()
                .antMatchers("/deleteUser/**")
                .permitAll()
                .antMatchers("/branches/**")
                .permitAll()
                .antMatchers("/branches/**")
                .permitAll()
                .antMatchers("/meetings/**")
                .permitAll()
                .antMatchers("/role/**")
                .permitAll()
                .antMatchers("/inventories/**")
                .permitAll()
                .antMatchers("/roles/**")
                .permitAll()
                .antMatchers("/disbursementTickets/**")
                .permitAll()
                .antMatchers("/disbursed-by-range/**")
                .permitAll()
                .antMatchers("/appraisalLoanRequest/**")
                .permitAll()
                .antMatchers("/sales/**")
                .permitAll()
                .antMatchers("/signature/**")
                .permitAll()
                .antMatchers("/businessUnit/**")
                .permitAll()
                .antMatchers("/comments/**")
                .permitAll()
                .antMatchers("/signatures/**")
                .permitAll()
                .antMatchers("/credit_history/**")
                .permitAll()
                .antMatchers("/disbursementTickets/**")
                .permitAll()
                .antMatchers("/loanRequest/**")
                .permitAll()
                .antMatchers("/long_term_credit_history/**")
                .permitAll()
                .antMatchers("/main_competitor/**")
                .permitAll()
                .antMatchers("/mitigating_risk/**")
                .permitAll()
                .antMatchers("/most_important_clients/**")
                .permitAll()
                .antMatchers("/ownership_details/**")
                .permitAll()
                .antMatchers("/risk_analysis/**")
                .permitAll()
                .antMatchers("/source_of_funds/**")
                .permitAll()
                .antMatchers("/most_important_suppliers/**")
                .permitAll()
                .antMatchers("/bank/**")
                .permitAll()
                .antMatchers("/bankTo/**")
                .permitAll()
                .antMatchers("/currentAssets/**")
                .permitAll()
                .antMatchers("/shortTermLiability/**")
                .permitAll()
                .antMatchers("/fixedAssets/**")
                .permitAll()
                .antMatchers("/longTermLiability/**")
                .permitAll()
                .antMatchers("/interestAndFinancialCost/**")
                .permitAll()
                .antMatchers("/collateralSecurity/**")
                .permitAll()
                .antMatchers("/capitalisationOfProfit/**")
                .permitAll()
                .antMatchers("/financialOutflow/**")
                .permitAll()
                .antMatchers("/financialInflow/**")
                .permitAll()
                .antMatchers("/investmnetInflow/**")
                .permitAll()
                .antMatchers("/investmentOutflow/**")
                .permitAll()
                .antMatchers("/faqs/**")
                .permitAll()
                .antMatchers("/news/**")
                .permitAll()
                .antMatchers("/testimonials/**")
                .permitAll()
                .antMatchers("/directors/**")
                .permitAll()
                .antMatchers("/teams/**")
                .permitAll()
                .antMatchers("/website-images/**")
                .permitAll()
                .antMatchers("/contactEmail/**")
                .permitAll()
                .antMatchers("/generalBusinessInformation/**")
                .permitAll()
                .antMatchers("/businessAndPersonalReference/**")
                .permitAll()
                .antMatchers("/otherBusinessAndIncome/**")
                .permitAll()
                .antMatchers("/repaymentHistory/**")
                .permitAll()
                .antMatchers("/swotRiskAnalysis/**")
                .permitAll()
                .antMatchers("/clientCharacterAssessment/**")
                .permitAll()
                .antMatchers("/familyUnitAndPersonalExpenses/**")
                .permitAll()
                .antMatchers("/privateExpenses/**")
                .permitAll()
                .antMatchers("/commentsOnFamilySituation/**")
                .permitAll()
                .antMatchers("/salesPurchasesCogsGrossMargin/**")
                .permitAll()
                .antMatchers("/accountsReceivables/**")
                .permitAll()
                .antMatchers("/commentsOnAccountReceivables/**")
                .permitAll()
                .antMatchers("/fixedAssets/**")
                .permitAll()
                .antMatchers("/fixedAssetsMicro/**")
                .permitAll()
                .antMatchers("/commentsOnFixedAssets/**")
                .permitAll()
                .antMatchers("/inventory/**")
                .permitAll()
                .antMatchers("/guarantor/**")
                .permitAll()
                .antMatchers("/operationalExpenses/**")
                .permitAll()
                .antMatchers("/balanceSheet/**")
                .permitAll()
                .antMatchers("/liabilities/**")
                .permitAll()
                .antMatchers("/applicationOfFunds/**")
                .permitAll()
                .antMatchers("/clientRequest/**")
                .permitAll()
                .antMatchers("/loanOfficerProposal/**")
                .permitAll()
                .antMatchers("/dailySales/**")
                .permitAll()
                .antMatchers("/averageDailySales/**")
                .permitAll()
                .antMatchers("/past3WorkingDays/**")
                .permitAll()
                .antMatchers("/past3Months/**")
                .permitAll()
                .antMatchers("/cashOnHand/**")
                .permitAll()
                .antMatchers("/seasonalityOfSales/**")
                .permitAll()
                .antMatchers("/purchaseBehaviour/**")
                .permitAll()
                .antMatchers("/mainProductPurchases/**")
                .permitAll()
                .antMatchers("/pastPurchases/**")
                .permitAll()
                .antMatchers("/motorVehicle/**")
                .permitAll()
                .antMatchers("/realEstate/**")
                .permitAll()
                .antMatchers("/houseHoldAssets/**")
                .permitAll()
                .antMatchers("/businessAssets/**")
                .permitAll()
                .antMatchers("/inventoryTwo/**")
                .permitAll()
                .antMatchers("/guarantorAssessmentResults/**")
                .permitAll()
                .antMatchers("/capacityAssessment/**")
                .permitAll()
                .antMatchers("/capacityAssessment2/**")
                .permitAll()
                .antMatchers("/loanRequestMicro/**")
                .permitAll()
                .antMatchers("/costOfSales/**")
                .permitAll()
                .antMatchers("/currentAssetsMicro/**")
                .permitAll()
                .antMatchers("/loanRequestMicro/**")
                .permitAll()
                .antMatchers("/guarantorAssessmentDetails/**")
                .permitAll()
                .antMatchers("/assessmentFileUpload/**")
                .permitAll()

                .antMatchers("/appraisalFileUpload/**")
                .permitAll()
                .antMatchers("/ClientFileUpload/**")
                .permitAll()
                .antMatchers("/xdsFileUpload/**")
                .permitAll()
                .antMatchers("/musoni/**")
                .permitAll()
                .antMatchers("/metabase/**")
                .permitAll()
                .antMatchers("/musoni_pastel/**")
                .permitAll()
                .antMatchers("/credit_application_enquiry/**")
                .permitAll()
                .antMatchers("/amortize/**")
                .permitAll()
                .antMatchers("/requestCollateralSecurity/**")
                .permitAll()

                .antMatchers("/credit_application_feedback/**")
                .permitAll()

                .antMatchers("/sms/**")
                .permitAll()

                .antMatchers("/credit_application_pipeline/**")
                .permitAll()

                .antMatchers("/leadStatus/**")
                .permitAll()

                .antMatchers("/zones/**")
                .permitAll()

                .antMatchers("/events/**")
                .permitAll()

                .antMatchers("/market_campaigns/**")
                .permitAll()

                .antMatchers("/followUpDiary/**")
                .permitAll()

                .antMatchers("/marketLeads/**")
                .permitAll()

                .antMatchers("/credit_application_client_datasets/**")
                .permitAll()

                .antMatchers("/access_logs/**")
                .permitAll()

                .antMatchers("/requisitions/**")
                .permitAll()

                .antMatchers("/targets/**")
                .permitAll()

                .antMatchers("/poTransactions/**")
                .permitAll()

                .antMatchers("/pos/**")
                .permitAll()

                .antMatchers("/cms/**")
                .permitAll()

                .antMatchers("/postGl/**")
                .permitAll()

                .antMatchers("/swagger-ui/**")
                .permitAll()

                .antMatchers("/v3/**")
                .permitAll()

                .antMatchers("/treasury_management/**")
                .permitAll()

                .antMatchers("/settlements/**")
                .permitAll()

                .anyRequest()
                .authenticated();

        httpSecurity.addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);
    }

//    @Bean
//    public FormattingConversionService mvcConversionService() {
//        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
//        conversionService.addConverter((Converter<String, MyCustomType>) new StringToYourTypeConverter()); // Customize this converter
//
//        return conversionService;
//    }
}
