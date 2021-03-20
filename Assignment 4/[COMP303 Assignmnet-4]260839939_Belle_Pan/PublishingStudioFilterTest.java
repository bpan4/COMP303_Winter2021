package comp303assignment4;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PublishingStudioFilterTest {
	
	PublishingStudioFilterStrategy studioFilter;
	TVShow showWithPublisher;
	TVShow showWithoutPublisher;
	Episode episodeWithPublisher;
	Episode episodeWithoutPublisher;
	Movie movieWithPublisher;
	Movie movieWithoutPublisher;
	
	@BeforeEach
	void setUp() {
		studioFilter = new PublishingStudioFilterStrategy("WarnerBrothers");
		
		showWithPublisher = new TVShow("Yu-Gi-Oh!", Language.ENGLISH, "WarnerBrothers");
		showWithoutPublisher = new TVShow("Yu-Gi-Oh!", Language.ENGLISH, "Konami");
		
		File episodeFile1 = new File("C:\\Users\\user\\Downloads\\YuGiOh1.MOV");
		
		episodeWithPublisher = showWithPublisher.createAndAddEpisode(episodeFile1, "The Heart of the Cards");
		
		episodeWithoutPublisher = showWithoutPublisher.createAndAddEpisode(episodeFile1, "The Heart of the Cards");
		
		File movieFile = new File("C:\\Users\\user\\Downloads\\YuGiOhMovie.MOV");
		movieWithPublisher = new Movie(movieFile, "The Yu-Gi-Oh! Movie", Language.ENGLISH, "WarnerBrothers");
		movieWithoutPublisher = new Movie(movieFile, "The Yu-Gi-Oh! Movie", Language.ENGLISH, "Konami");
	}
	
	@Test
	void testInputNull() {
		try {
			studioFilter = new PublishingStudioFilterStrategy(null);
			fail();
		} catch (AssertionError e) {
			assertEquals(1,1);
		}
	}
	
	@Test
	void testFilterPublishingStudioUsingMovieThatMatches() {
		assertEquals(true, studioFilter.filter(movieWithPublisher));
	}
	
	@Test
	void testFilterPublishingStudioUsingMovieThatDoesNotMatch() {
		assertEquals(false, studioFilter.filter(movieWithoutPublisher));
	}
	
	@Test
	void testFilterPublishingStudioUsingTVShowThatMatches() {
		assertEquals(true, studioFilter.filter(showWithPublisher));
	}
	
	@Test
	void testFilterPublishingStudioUsingTVShowThatDoesNotMatch() {
		assertEquals(false, studioFilter.filter(showWithoutPublisher));
	}
	@Test
	void testFilterPublishingStudioUsingEpisodeThatMatches() {
		assertEquals(true, studioFilter.filter(episodeWithPublisher));
	}
	
	@Test
	void testFilterPublishingStudioUsingEpisodeThatDoesNotMatch() {
		assertEquals(false, studioFilter.filter(episodeWithoutPublisher));
	}

}