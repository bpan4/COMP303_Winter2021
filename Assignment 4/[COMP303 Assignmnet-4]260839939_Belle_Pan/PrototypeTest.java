package comp303assignment4;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PrototypeTest {

	TVShow show;
	
	@BeforeEach
	void setUp() {
		show = new TVShow("Yu-Gi-Oh!", Language.ENGLISH, "The Heart of the Cards");
	}
	
	@Test 
	void testPrototypeDoesNotExist() {
		try {
			show.getPrototype();
			fail();
		} catch (AssertionError e) {
			assertEquals(1,1);
		}
	}
	
	@Test
	void testPrototypeExists() {
		File episodeFile = new File("C:\\Users\\user\\Downloads\\YuGiOh1.MOV");
		Episode episode = show.createAndAddEpisode(episodeFile, "This is the first episode created");
		assertEquals (episode.getTitle(), show.getPrototype().getTitle());
		assertEquals (episode.getAllCharacters(), show.getPrototype().getAllCharacters());
		assertEquals (episode.getEpisodeNumber(), show.getPrototype().getEpisodeNumber());
		assertEquals (episode.getFilePath(), show.getPrototype().getFilePath());
		assertEquals (episode.getLanguage(), show.getPrototype().getLanguage());
		assertEquals (episode.getStudio(), show.getPrototype().getStudio());
		assertEquals (episode.getTVShow(), show.getPrototype().getTVShow());
	}

}
