package com.retask.game.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retask.game.message.request.RewardRequest;
import com.retask.game.message.response.RetaskStatusResponse;
import com.retask.game.message.response.RewardResponse;
import com.retask.game.model.Reward;
import com.retask.game.model.Upload;
import com.retask.game.repository.RewardRepository;
import com.retask.game.repository.UploadRepository;

@Service
public class RewardService {

	@Autowired
	private RewardRepository rewardRepository;
	@Autowired
	private UploadRepository uploadRepository;

	/**
	 * gets the tasks by the username
	 * 
	 * @param username
	 * @return
	 */
	public List<RewardResponse> getRewardsByUsername(String username) {

		List<Reward> rewards = rewardRepository.findByUsernameSorted(username);
		List<Upload> uploads;
		List<RewardResponse> rewardsResponse = new ArrayList<RewardResponse>();
		RewardResponse rewardResponse;

		System.out.println("In rewards by username");

		for (Reward reward : rewards) {

			uploads = uploadRepository.findBySourceTypeAndId("reward", reward.getId());

			rewardResponse = new RewardResponse(reward);
			rewardResponse.setUploads(uploads);

			rewardsResponse.add(rewardResponse);

		}

		return rewardsResponse;
	}

	/**
	 * create rewards for a user.
	 * 
	 * @param rewardRequests
	 * @return
	 */
	public boolean createRewardsForUsername(List<RewardRequest> rewardRequests) {

		Reward rewardToSave;
		Upload upload;

		for (RewardRequest rewardRequest : rewardRequests) {

			rewardRequest.setUpdateDateTime();
			upload = new Upload();

			rewardRequest.setId(null);

			rewardRequest.setCreateDateTime();
			rewardRequest.setUpdateDateTime();
			rewardToSave = new Reward(rewardRequest);
			rewardRepository.save(rewardToSave);

			if (!rewardRequest.getUploads().isEmpty()) {
				upload = rewardRequest.getUploads().get(0);
				upload.setUpdateDateTime();
				upload.setCreateDateTime();
				upload.setUploadable_id(rewardToSave.getId());
				upload.setUploadable_type("reward");

				uploadRepository.save(upload);
			}
		}

		return true;
	}

	/**
	 * updateRewards for a user.
	 * 
	 * @param rewardRequests
	 * @return
	 */
	public RetaskStatusResponse updateRewardsForUsername(List<RewardRequest> rewardRequests) {

		Reward rewardExisting, rewardToSave;
		List<Upload> uploads;
		Upload upload;
		Timestamp holdDateTimeCreated;
		boolean uploadExisted = false;

		for (RewardRequest rewardRequest : rewardRequests) {

			rewardRequest.setUpdateDateTime();
			upload = new Upload();
			uploadExisted = false;
			holdDateTimeCreated = null;

			// if id is null then exit with an error
			if (rewardRequest.getId() == null) {
				return new RetaskStatusResponse(-1, "Request not found");
			}

			if (rewardRequest.getUploads().get(0).getId() == null) {
				return new RetaskStatusResponse(-1, "Upload not found");
			}

			if (rewardRepository.existsById(rewardRequest.getId())) {

				rewardExisting = rewardRepository.findById(rewardRequest.getId()).get();
				holdDateTimeCreated = rewardExisting.getDateTimeCreated();
				uploads = uploadRepository.findBySourceTypeAndId("reward", rewardRequest.getId());
				if (uploads.size() < 1) {
					return new RetaskStatusResponse(-1, "Upload not found");
				}
			} else {
				return new RetaskStatusResponse(-1, "Request not found");
			}

			rewardToSave = new Reward(rewardRequest);
			rewardToSave.setDateTimeCreated(holdDateTimeCreated);
			rewardRepository.save(rewardToSave);

			upload = rewardRequest.getUploads().get(0);
			upload.setUpdateDateTime();

			upload.setUploadable_id(rewardToSave.getId());
			upload.setUploadable_type("reward");
			upload.setDateTimeCreated(uploads.get(0).getDateTimeCreated());
			System.out.println(rewardToSave.getId());
			uploadRepository.save(upload);
		}

		return new RetaskStatusResponse(0, "Ok");
	}
	
	
	/**
	 * Delete a reward by Username and reward id
	 * 
	 * @param username
	 * @param rewardId
	 * @return
	 */
	public RetaskStatusResponse deleteRewardByUsernameAndID(String username, Long rewardId) {
		
		System.out.println(rewardId);

		Reward reward = rewardRepository.findById(rewardId).orElse(null);

		// make sure you find the reward and that it is for the same username
		if (reward != null) {
			if (!reward.getUsername().equals(username)) {
				return new RetaskStatusResponse(-1, "Reward not found");
			}
		} else {
			return new RetaskStatusResponse(-1, "Reward not found");
		}
		
		//rewardRepository.deleteById(rewardId);
		
		return new RetaskStatusResponse(0, "Ok");
	}


}
