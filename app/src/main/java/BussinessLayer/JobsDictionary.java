package BussinessLayer;

import java.util.LinkedList;

public class JobsDictionary {

    private LinkedList<JobRequest> jobs;
    private LinkedList<String> jobsTitle;
    private static JobsDictionary instance = new JobsDictionary();

    private JobsDictionary() {
        jobs = new LinkedList<>();
        jobsTitle = new LinkedList<>();
    }

    public static JobsDictionary getInstance() {
        return instance;
    }

    public void addJob(JobRequest job) {
        jobs.add(job);
        jobsTitle.add(job.getTitle());
    }

    public LinkedList<JobRequest> getJobs() {
        return jobs;
    }

    public LinkedList<String> getJobsTitles() {
        return jobsTitle;
    }

    public void editJob(int position, JobRequest job) {
        jobs.get(position).setTitle(job.getTitle());
        jobs.get(position).setContent(job.getContent());
        jobs.get(position).setDate(job.getDate());
        jobsTitle.set(position, job.getTitle());
    }
}
