package com.retask.game.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

		List<Reward> rewards = rewardRepository.findByUsername(username);
		List<Upload> uploads;
		List<RewardResponse> rewardsResponse = new ArrayList<RewardResponse>();
		RewardResponse rewardResponse;
		
		System.out.println("In rewards by username");
		
		
		for (Reward reward: rewards) {
			
			uploads = uploadRepository.findBySourceTypeAndId("reward", reward.getId());
			
			rewardResponse = new RewardResponse(reward);
			rewardResponse.setUploads(uploads);
			
			rewardsResponse.add(rewardResponse);
			
		}
				
		return rewardsResponse;
	}
	
	public boolean createRewardsForUsername(List<Reward> rewards) {
		
		for (Reward reward: rewards) {	
			rewardRepository.save(reward);
		}
		
		return true;
	}
	
}
