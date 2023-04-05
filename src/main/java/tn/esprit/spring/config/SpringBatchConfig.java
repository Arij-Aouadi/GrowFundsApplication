/* package tn.esprit.spring.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.io.FileSystemResource;
import tn.esprit.spring.DAO.Entities.Account;


import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
@EnableBatchProcessing
@EnableAutoConfiguration


public class SpringBatchConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Bean

public Job job1( JobBuilderFactory jobBuilderFactory,
                 StepBuilderFactory stepBuilderFactory,
                ItemReader<Account> itemReader,
                ItemProcessor<Account, Account> itemProcessor,
                ItemWriter<Account> itemWriter
)
    {

    Step step = stepBuilderFactory.get("ETL-file-load")
            .<Account, Account>chunk(100)
            .reader(itemReader)
            .processor(itemProcessor)
            .writer(itemWriter)
            .build();


    return jobBuilderFactory.get("ETL-Load")
            .incrementer(new RunIdIncrementer())
            .start(step)
            .build();
}

        public ConversionService createConversionService() {
            DefaultConversionService conversionService = new DefaultConversionService();
            DefaultConversionService.addDefaultConverters(conversionService);
//            conversionService.addConverter(new Converter<String, LocalDate>() {
//                @Override
//                public LocalDate convert(String text) {
//                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//                    return LocalDate.parse(text, formatter);
//                }
            });
            return conversionService;
        }


        @Bean
        public FlatFileItemReader<Account> itemReader() {

            FlatFileItemReader<Account> flatFileItemReader = new FlatFileItemReader<>();
            flatFileItemReader.setResource(new FileSystemResource("src/main/resources/Accounts.csv"));
            flatFileItemReader.setName("CSV-Reader");
            flatFileItemReader.setLinesToSkip(1);
            flatFileItemReader.setLineMapper(lineMapper());
            return flatFileItemReader;
        }

        @Bean
        public LineMapper<Account> lineMapper() {

            DefaultLineMapper<Account> defaultLineMapper = new DefaultLineMapper<>();
            DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

            lineTokenizer.setDelimiter(",");
            lineTokenizer.setStrict(false);
            lineTokenizer.setNames("rib","solde","state","type_account");

            BeanWrapperFieldSetMapper<Account> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
            fieldSetMapper.setConversionService(createConversionService());
            fieldSetMapper.setTargetType(Account.class);

            defaultLineMapper.setLineTokenizer(lineTokenizer);
            defaultLineMapper.setFieldSetMapper(fieldSetMapper);

            return defaultLineMapper;
        }

}

 */

