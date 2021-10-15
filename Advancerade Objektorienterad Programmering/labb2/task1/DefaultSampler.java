package labb2.task1;

public class DefaultSampler implements Sampler {
	public double read() {
		return Math.random() * 100;
	}
}