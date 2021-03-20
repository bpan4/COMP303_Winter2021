package comp303assignment4;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EpisodeNumberFilterTest {
	EpisodeNumberFilterStrategy episodeFilter;
	TVShow show;
	Episode episode1;
	Episode episode2;
	Movie movie;
	
	@BeforeEach
	void setUp() {
		
		show = new TVShow("This is the show", Language.ENGLISH, "This is the studio");
		
		File episodeFile1 = new File("C:\\Users\\user\\Downloads\\YuGiOh1.MOV");
		File episodeFile2 = new File("C:\\Users\\user\\Downloads\\YuGiOh2.MOV");
		episode1 = show.createAndAddEpisode(episodeFile1, "The Heart of the Cards");
		episode2 = show.createAndAddEpisode(episodeFile2, "The Heart of the Cards");
		
		File movieFile = new File("C:\\Users\\user\\Downloads\\YuGiOhMovie.MOV");
		movie = new Movie(movieFile, "The Yu-Gi-Oh! Movie", Language.ENGLISH, "WarnerBrothers");
	}
	
	@Test
	void testInputLessThanOne() {
		try {
			episodeFilter = new EpisodeNumberFilterStrategy(0);
			fail();
		} catch (AssertionError e) {
			assertEquals(1,1);
		}
	}
	
	@Test
	void testFilterEpisodeNumberUsingMovie() {
		episodeFilter = new EpisodeNumberFilterStrategy(1);
		assertEquals(false, episodeFilter.filter(movie));
	}
	
	@Test
	void testFilterEpisodeNumberUsingTVShow() {
		episodeFilter = new EpisodeNumberFilterStrategy(1);
		assertEquals(false, episodeFilter.filter(show));
	}
	
	@Test
	void testFilterEpisodeNumberWhenEpisodeMatches() {
		episodeFilter = new EpisodeNumberFilterStrategy(2);
		assertEquals(true, episodeFilter.filter(episode2));
	}
	
	@Test
	void testFilterEpisodeNumberWhenEpisodeDoesNotMatch() {
		episodeFilter = new EpisodeNumberFilterStrategy(2);
		assertEquals(false, episodeFilter.filter(episode1));
	}
	
	@Test
	void testFilterEpisodeNumberWithLargeInput() {
		episodeFilter = new EpisodeNumberFilterStrategy(8000);
		assertEquals(false, episodeFilter.filter(episode1));
	}

}
