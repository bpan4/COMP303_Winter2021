package comp303assignment4;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConjunctionFilterTest {
	
	FilterCollectionConjunction conjunctionFilter;
	EpisodeNumberFilterStrategy episodeFilter;
	LanguageFilterStrategy languageFilter;
	PublishingStudioFilterStrategy studioFilter;
	
	TVShow showWithPublisher;
	TVShow showWithoutPublisher;
	
	Episode episode1WithPublisher;
	Episode episode1WithoutPublisher;
	Episode episode2WithPublisher;
	Episode episode2WithoutPublisher;
	
	Movie movieWithPublisher;
	Movie movieWithoutPublisher;
	
	List<WatchListFilter> filters;
	
	@BeforeEach
	void setUp() {
		conjunctionFilter = new FilterCollectionConjunction();
		languageFilter = new LanguageFilterStrategy(Language.ENGLISH);
		studioFilter = new PublishingStudioFilterStrategy("WarnerBrothers");
		episodeFilter = new EpisodeNumberFilterStrategy(1);
		
		showWithPublisher = new TVShow("Yu-Gi-Oh!", Language.ENGLISH, "WarnerBrothers");
		showWithoutPublisher = new TVShow("Yu-Gi-Oh!", Language.ENGLISH, "Konami");
		
		File episodeFile1 = new File("C:\\Users\\user\\Downloads\\YuGiOh1.MOV");
		File episodeFile2 = new File("C:\\Users\\user\\Downloads\\YuGiOh2.MOV");
		
		episode1WithPublisher = showWithPublisher.createAndAddEpisode(episodeFile1, "The Heart of the Cards");
		episode1WithoutPublisher = showWithoutPublisher.createAndAddEpisode(episodeFile1, "The Heart of the Cards");
		
		episode2WithPublisher = showWithPublisher.createAndAddEpisode(episodeFile2, "The Gauntlet Is Thrown");
		episode2WithoutPublisher = showWithoutPublisher.createAndAddEpisode(episodeFile2, "The Gauntlet Is Thrown");
		
		File movieFile = new File("C:\\Users\\user\\Downloads\\YuGiOhMovie.MOV");
		movieWithPublisher = new Movie(movieFile, "The Yu-Gi-Oh! Movie", Language.ENGLISH, "WarnerBrothers");
		movieWithoutPublisher = new Movie(movieFile, "The Yu-Gi-Oh! Movie", Language.ENGLISH, "Konami");
	}
	
	@Test
	void testGetFiltersWhenEmpty() {
		try {
			conjunctionFilter.getFilters();
			fail();
		} catch (AssertionError e) {
			assertEquals(1,1);
		}
	}
	
	@Test
	void testGetFiltersWhenNotEmpty() {
		try {
			List<WatchListFilter> supposedlyAddedFilters = new ArrayList<>();
			conjunctionFilter.addFilter(studioFilter);
			supposedlyAddedFilters.add(studioFilter);
			
			List<WatchListFilter> addedFilters = conjunctionFilter.getFilters();
			assertEquals(supposedlyAddedFilters, addedFilters);
		} catch (AssertionError e) {
			fail();
		}
	}
	
	@Test
	void testNoFiltersAppliedforMovies() {
		try {
			conjunctionFilter.filter(movieWithPublisher);
			fail();
		} catch (AssertionError e) {
			assertEquals(1,1);
		}
	}
	
	@Test
	void testNoFiltersAppliedforTVShows() {
		try {
			conjunctionFilter.filter(showWithPublisher);
			fail();
		} catch (AssertionError e) {
			assertEquals(1,1);
		}
	}
	
	@Test
	void testNoFiltersAppliedforEpisodes() {
		try {
			conjunctionFilter.filter(episode1WithPublisher);
			fail();
		} catch (AssertionError e) {
			assertEquals(1,1);
		}
	}
	
	@Test
	void testAddingFilter() {
		filters = new ArrayList<>();
		try {
			conjunctionFilter.addFilter(studioFilter);
			filters.add(studioFilter);
			assertEquals(filters, conjunctionFilter.getFilters());
		} catch (AssertionError e) {
			fail();
		}
	}
	
	@Test
	void testAddingMultipleFilters() {
		filters = new ArrayList<>();
		try {
			conjunctionFilter.addFilter(studioFilter);
			conjunctionFilter.addFilter(episodeFilter);
			filters.add(studioFilter);
			filters.add(episodeFilter);
			assertEquals(filters, conjunctionFilter.getFilters());
		} catch (AssertionError e) {
			fail();
		}
	}
	
	@Test
	void testAddingSameFilterTwice() {
		filters = new ArrayList<>();
		try {
			conjunctionFilter.addFilter(studioFilter);
			conjunctionFilter.addFilter(studioFilter);
			filters.add(studioFilter);
			assertEquals(filters, conjunctionFilter.getFilters());
		} catch (AssertionError e) {
			fail();
		}
	}
	
	@Test
	void testRemovingFilterWhenItExists() {
		filters = new ArrayList<>();
		try {
			conjunctionFilter.addFilter(studioFilter);
			conjunctionFilter.removeFilter(studioFilter);
			assertEquals(filters, conjunctionFilter.getFilters());
		} catch (AssertionError e) {
			fail();
		}
	}
	
	@Test
	void testRemovingMultipleFilters() {
		filters = new ArrayList<>();
		try {
			conjunctionFilter.addFilter(studioFilter);
			filters.add(studioFilter);
			conjunctionFilter.addFilter(episodeFilter);
			conjunctionFilter.removeFilter(episodeFilter);
			assertEquals(filters, conjunctionFilter.getFilters());
		} catch (AssertionError e) {
			fail();
		}
	}
	
	@Test
	void testRemovingSameFilterTwice() {
		filters = new ArrayList<>();
		try {
			conjunctionFilter.addFilter(studioFilter);
			conjunctionFilter.removeFilter(studioFilter);
			conjunctionFilter.removeFilter(studioFilter);
			assertEquals(filters, conjunctionFilter.getFilters());
		} catch (AssertionError e) {
			fail();
		}
	}
	
	@Test
	void testMovieConjunctionFilterUsingOneFilterThatMatches() {
		conjunctionFilter.addFilter(studioFilter);
		assertEquals(true, conjunctionFilter.filter(movieWithPublisher));
	}
	
	@Test
	void testMovieConjunctionFilterUsingOneFilterThatDoesNotMatch() {
		conjunctionFilter.addFilter(studioFilter);
		assertEquals(false, conjunctionFilter.filter(movieWithoutPublisher));
	}
	
	@Test
	void testMovieConjunctionFilterUsingMultipleFiltersAndMatchesOne() {
		conjunctionFilter.addFilter(studioFilter);
		conjunctionFilter.addFilter(episodeFilter);
		assertEquals(false, conjunctionFilter.filter(movieWithPublisher));
	}
	
	@Test
	void testMovieConjunctionFilterUsingMultipleFiltersAndDoesNotMatch() {
		conjunctionFilter.addFilter(studioFilter);
		conjunctionFilter.addFilter(episodeFilter);
		assertEquals(false, conjunctionFilter.filter(movieWithoutPublisher));
	}
	
	@Test
	void testMovieConjunctionFilterUsingMultipleFiltersAndMatchesAll() {
		conjunctionFilter.addFilter(studioFilter);
		conjunctionFilter.addFilter(languageFilter);
		assertEquals(true, conjunctionFilter.filter(movieWithPublisher));
	}
	
	@Test
	void testTVShowConjunctionFilterUsingOneFilterThatMatches() {
		conjunctionFilter.addFilter(studioFilter);
		assertEquals(true, conjunctionFilter.filter(showWithPublisher));
	}
	
	@Test
	void testTVShowConjunctionFilterUsingOneFilterThatDoesNotMatch() {
		conjunctionFilter.addFilter(studioFilter);
		assertEquals(false, conjunctionFilter.filter(showWithoutPublisher));
	}
	
	@Test
	void testTVShowConjunctionFilterUsingMultipleFiltersAndMatchesOne() {
		conjunctionFilter.addFilter(studioFilter);
		conjunctionFilter.addFilter(episodeFilter);
		assertEquals(false, conjunctionFilter.filter(showWithPublisher));
	}
	
	@Test
	void testTVShowConjunctionFilterUsingMultipleFiltersAndDoesNotMatch() {
		conjunctionFilter.addFilter(studioFilter);
		conjunctionFilter.addFilter(episodeFilter);
		assertEquals(false, conjunctionFilter.filter(showWithoutPublisher));
	}
	
	@Test
	void testTVShowConjunctionFilterUsingMultipleFiltersAndMatchesAll() {
		conjunctionFilter.addFilter(studioFilter);
		conjunctionFilter.addFilter(languageFilter);
		assertEquals(true, conjunctionFilter.filter(showWithPublisher));
	}
	
	@Test
	void testEpisodeConjunctionFilterUsingOneFilterThatMatches() {
		conjunctionFilter.addFilter(studioFilter);
		assertEquals(true, conjunctionFilter.filter(episode1WithPublisher));
	}
	
	@Test
	void testEpisodeConjunctionFilterUsingOneFilterThatDoesNotMatch() {
		conjunctionFilter.addFilter(studioFilter);
		assertEquals(false, conjunctionFilter.filter(episode1WithoutPublisher));
	}
	
	@Test
	void testEpisodeConjunctionFilterUsingMultipleFiltersAndMatchesOne() {
		conjunctionFilter.addFilter(studioFilter);
		conjunctionFilter.addFilter(episodeFilter);
		assertEquals(false, conjunctionFilter.filter(episode1WithoutPublisher));
	}
	
	@Test
	void testEpisodeConjunctionFilterUsingMultipleFiltersAndDoesNotMatch() {
		conjunctionFilter.addFilter(studioFilter);
		conjunctionFilter.addFilter(episodeFilter);
		assertEquals(false, conjunctionFilter.filter(episode2WithoutPublisher));
	}
	
	@Test
	void testEpisodeConjunctionFilterUsingMultipleFiltersAndMatchesAll() {
		conjunctionFilter.addFilter(studioFilter);
		conjunctionFilter.addFilter(languageFilter);
		assertEquals(true, conjunctionFilter.filter(episode1WithPublisher));
	}
}
