package org.gteperu.erp.everest.batch;

import java.util.Date;

import org.gteperu.erp.everest.exception.ExceptionResponse;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleJob {

	@Autowired
    private JobLauncher launcher;
     
    @Autowired
    private Job tipoCambioJob;
    
    private JobExecution execution;
     
    //@Scheduled(cron="0 38 15 * * ?") <==Para Pruebas
	// "*/10 * * * * *" = every ten seconds.
    //0 0 3,16 * * * = todos los días a la hora de 3 y 16
    @Scheduled(cron="0 0 * * * *")
    public void runAsistenciaSchedule(){
        try {
        	execution = launcher.run(tipoCambioJob, new JobParametersBuilder()
        			.addLong("timestamp",System.currentTimeMillis())
        			.toJobParameters());
            System.out.println("Execution status: TipoCambio - "+ execution.getStatus());
        } catch (JobExecutionAlreadyRunningException e) {
        	new ExceptionResponse(new Date(),e.getMessage(),this.getClass().getSimpleName()+" Error al ejecutar el Job tipoCambioJob, ERROR: "+e.getStackTrace()[0].getClassName(),e);
        } catch (JobRestartException e) {           
        	new ExceptionResponse(new Date(),e.getMessage(),this.getClass().getSimpleName()+" Error al ejecutar el Job tipoCambioJob, ERROR: "+e.getStackTrace()[0].getClassName(),e);
        } catch (JobInstanceAlreadyCompleteException e) {           
        	new ExceptionResponse(new Date(),e.getMessage(),this.getClass().getSimpleName()+" Error al ejecutar el Job tipoCambioJob, ERROR: "+e.getStackTrace()[0].getClassName(),e);
        } catch (JobParametersInvalidException e) {         
        	new ExceptionResponse(new Date(),e.getMessage(),this.getClass().getSimpleName()+" Error al ejecutar el Job tipoCambioJob, ERROR: "+e.getStackTrace()[0].getClassName(),e);
        }
    }
}
