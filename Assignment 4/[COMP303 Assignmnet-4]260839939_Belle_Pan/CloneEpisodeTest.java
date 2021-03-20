package comp303assignment4;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class CloneEpisodeTest {
	
	TVShow show;
	Episode episode;
	
	@BeforeEach
	void setUp() {
		show = new TVShow("Yu-Gi-Oh!", Language.ENGLISH, "WarnerBrothers");
		File episodeFile = new File("C:\\Users\\user\\Downloads\\YuGiOh1.MOV");
		episode = show.createAndAddEpisode(episodeFile, "The Heart of the Cards");	
	}
	@Test
	void testMakingClone() {
		Object clonedEpisode = episode.clone();
		assertEquals(Episode.class, clonedEpisode.getClass());
	}
	
	@Test
	void testCloneHasSameValues() {
		Episode clonedEpisode = episode.clone();
		assertEquals (episode.getTitle(), clonedEpisode.getTitle());
		assertEquals (episode.getAllCharacters(), clonedEpisode.getAllCharacters());
		assertEquals (episode.getEpisodeNumber(), clonedEpisode.getEpisodeNumber());
		assertEquals (episode.getFilePath(), clonedEpisode.getFilePath());
		assertEquals (episode.getLanguage(), clonedEpisode.getLanguage());
		assertEquals (episode.getStudio(), clonedEpisode.getStudio());
		assertEquals (episode.getTVShow(), clonedEpisode.getTVShow());
	}

}
