package com.retask.game.message.response;

import java.util.List;

import com.retask.game.model.Reward;
import com.retask.game.model.Task;
import com.retask.game.model.Upload;

public class RewardResponse extends Reward {

	public RewardResponse() {
	}

	public RewardResponse(Reward reward) {
		super(reward);
	}

	private List<Upload> uploads;

	public List<Upload> getUploads() {
		return uploads;
	}

	public void setUploads(List<Upload> uploads) {
		this.uploads = uploads;
	}

}