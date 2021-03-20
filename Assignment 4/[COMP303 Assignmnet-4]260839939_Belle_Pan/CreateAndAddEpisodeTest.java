package comp303assignment4;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreateAndAddEpisodeTest {
	
	TVShow show;
	File episodeFile;
	Episode episode;
	
	@BeforeEach
	void setUp() {
		show = new TVShow("This is the show", Language.ENGLISH, "This is the studio");
		episodeFile = new File("C:\\Users\\user\\Downloads\\YuGiOh1.MOV");
		episode = show.createAndAddEpisode(episodeFile, "This is the first episode created");
	}
	
	@Test
	void testGetEpisodeNumber() {
		try {
			show.getEpisode(1);
			assertEquals(episode, show.getEpisode(1));
		} catch (IndexOutOfBoundsException e) {
			fail("The episode was not added to the TVShow");
		}
		
	}
	
	@Test
	void testEpisodeNumberWithTwoEpisodes() {
		File secondEpisodeFile = new File("C:\\Users\\user\\Downloads\\YuGiOh2.MOV");
		Episode episode2 = show.createAndAddEpisode(secondEpisodeFile, "This is the second episode created");
		try {
			show.getEpisode(2);
			assertEquals(episode2, show.getEpisode(2));
		} catch (IndexOutOfBoundsException e) {
			fail("The episode was not added to the TVShow");
		}
	}
	
	@Test
	void testNumberOfEpisodes() {
		assertEquals(1, show.getTotalCount());
	}
	
	@Test
	void testTitleOfEpisode() {
		try {
			show.getEpisode(1);
			assertEquals("This is the first episode created", show.getEpisode(1).getTitle());
		} catch (IndexOutOfBoundsException e) {
			fail("The episode was not added to the TVShow");
		}
	}
	
	
	@Test
	void testFilePathOfEpisode() {
		try {
			show.getEpisode(1);
			assertEquals(episodeFile, show.getEpisode(1).getFilePath());
		} catch (IndexOutOfBoundsException e) {
			fail("The episode was not added to the TVShow");
		}
	}
	
	
	@Test
	void testEpisodeNumber() {
		try {
			show.getEpisode(1);
			assertEquals(episode.getEpisodeNumber(), show.getEpisode(1).getEpisodeNumber());
		} catch (IndexOutOfBoundsException e) {
			fail("The episode was not added to the TVShow");
		}
	}
}
