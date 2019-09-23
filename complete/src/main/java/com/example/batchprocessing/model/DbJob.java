package com.example.batchprocessing.model;

import java.util.Date;

public class DbJob {

	public DbJob() {

	}

	public DbJob(String jobName, String jobGroup, String cron, String invokeEndpoint, String createdBY,
			String lastModifiedBy, Date createdAt, Date lastModifiedAt) {
		super();
		this.jobName = jobName;
		this.jobGroup = jobGroup;
		this.cron = cron;
		this.invokeEndpoint = invokeEndpoint;
		this.createdBY = createdBY;
		this.lastModifiedBy = lastModifiedBy;
		this.createdAt = createdAt;
		this.lastModifiedAt = lastModifiedAt;
	}

	private String jobName;
	private String jobGroup;
	private String cron;
	private String invokeEndpoint;
	private String createdBY;
	private String lastModifiedBy;
	private Date createdAt;
	private Date lastModifiedAt;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public String getInvokeEndpoint() {
		return invokeEndpoint;
	}

	public void setInvokeEndpoint(String invokeEndpoint) {
		this.invokeEndpoint = invokeEndpoint;
	}

	public String getCreatedBY() {
		return createdBY;
	}

	public void setCreatedBY(String createdBY) {
		this.createdBY = createdBY;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getLastModifiedAt() {
		return lastModifiedAt;
	}

	public void setLastModifiedAt(Date lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}

}
