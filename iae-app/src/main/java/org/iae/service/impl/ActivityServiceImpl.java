package org.iae.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.iae.exception.ObjectNotFoundException;
import org.iae.exception.OperationFailedException;
import org.iae.pojo.Activity;
import org.iae.repository.ActivityRepository;
import org.iae.service.ActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl implements ActivityService {

	private static final Logger logger = LoggerFactory.getLogger(ActivityService.class);
	
	@Autowired
	private ActivityRepository activityRepository;
	
	@Override
	public Activity addActivity(Activity activity) throws OperationFailedException {
		
		Activity updatedActivity;
		
		logger.debug("Entered into addActivity()");
		
		try {
			updatedActivity = activityRepository.save(activity);
		} catch(Exception e) {
			logger.debug("Exception adding activity : {}", e.getMessage());
			throw new OperationFailedException(e.getMessage());
		}
		
		logger.debug("Exit from addActivity()");
		
		return updatedActivity;
	}

	@Override
	public void updateActivity(Activity activity) throws ObjectNotFoundException, OperationFailedException {
		
		logger.debug("Entered into updateActivity()");
		
		try {
			activityRepository.save(activity);
		} catch(Exception e) {
			logger.debug("Exception updating activity : {}", e.getMessage());
			throw new OperationFailedException(e.getMessage());
		}
		
		logger.debug("Exit from updateActivity()");
	}

	@Override
	public List<Activity> getAllActivities() {
		logger.debug("Entered into getAllActivities()");
		
		List<Activity> activityList = (List<Activity>) activityRepository.findAll();
		
	//	setImages(activityList);
		
		logger.debug("Exit from getAllActivities()");
		
		return activityList;
	}

	@Override
	public List<Activity> getAllActivitiesByProject(String projectId) {
		logger.debug("Entered into getAllActivitiesByProject(), projectId : {} ", projectId);
		
		List<Activity> activityList = (List<Activity>) activityRepository.findAllByProjectId(Long.parseLong(projectId));
		
		logger.debug("Exit from getAllActivitiesByProject(), projectId : {} ", projectId);
		
		return activityList;
	}

	@Override
	public List<Activity> getAllActivitiesByStatus(String status) {
		logger.debug("Entered into getAllActivitiesByStatus(), status : {} ", status);
		List<Activity> activityList = (List<Activity>) activityRepository.findAllByStatus(status);
		//setImages(activityList);
		logger.debug("Exit from getAllActivitiesByStatus(), status : {} ", status);
		return activityList;
	}

	public List<Activity> getAllRecentActivities() {
		logger.debug("Entered into getAllRecentActivities()");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -6);
		List<Activity> activityList = (List<Activity>) activityRepository.findAllByStartDateBetween(c.getTime(), new Date());
		logger.debug("Exit from getAllRecentActivities()");
		return activityList;
	}
	
	public List<Activity> getAllUpcomingActivities() {
		logger.debug("Entered into getAllUpcomingActivities()");
		List<Activity> upcomingActivities = activityRepository.findAllByStartDateAfter(new Date());
		logger.debug("Exit from getAllUpcomingActivities()");
		return upcomingActivities;
	}

/*	private void setImages(List<Activity> activityList) {
		
		if(activityList != null && activityList.size() > 0) {
			for(Activity activity : activityList) {
				setImages(activity);
			}
		}
	}
	
	private void setImages(Activity activity) {

		if(activity != null) {
			List<String> imagesForActivity = new ArrayList<>();
			File dir = new File(activity.getImagesLoc());

			if(dir.exists() && dir.isDirectory()) {
				File[] files = dir.listFiles();
				for(File file : files) {
					imagesForActivity.add(file.getAbsolutePath() + File.separator + file.getName());
				}
				//Setting all images URLs to the activity
				activity.setImagesURL(imagesForActivity);
			}
		}
	}*/

	@Override
	public Activity getActivityById(Long activityId) {
		logger.debug("Entered into getActivityById(), activity Id, {} ", activityId);
		Activity activity = activityRepository.findOne(activityId);
		//setImages(activity);
		logger.debug("Exit from getActivityById(), activity Id, {} ", activityId);
		return activity;
	}
}