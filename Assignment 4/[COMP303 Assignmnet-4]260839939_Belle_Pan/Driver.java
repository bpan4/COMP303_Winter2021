package comp303assignment4;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Driver {
	
	public void main(String[] args) {
	//The following is to test the functionality of the FilterCollectionConjunction and FilterCollectionDisjunction classes
	//as well as the PublishingStudioFilterStrategy and EpisodeNumberStrategy classes
	
	//let us first create three TVShow objects
	TVShow yugiohShow = new TVShow("Yu-Gi-Oh!", Language.ENGLISH, "WarnerBrothers");
	TVShow demonSlayerShow = new TVShow("Demon Slayer", Language.ENGLISH, "Ufotable");
	TVShow bugsBunnyShow = new TVShow("The Bugs Bunny Show", Language.ANCIENT_GREEK, "WarnerBrothers");
	
	//next, we need to add episodes into each of the shows
	//to do so, we first need to create some file paths
	File yugioh1 = new File("C:\\Users\\user\\Downloads\\YuGiOh1.MOV");
	File yugioh2 = new File("C:\\Users\\user\\Downloads\\YuGiOh2.MP4");
	File yugioh3 = new File("C:\\Users\\user\\Downloads\\YuGiOh3.MOV");
	
	File demonslayer1 = new File("C:\\Users\\user\\Downloads\\DemonSlayer1.MOV");
	File demonslayer2 = new File("C:\\Users\\user\\Downloads\\DemonSlayer2.MOV");
	File demonslayer3 = new File("C:\\Users\\user\\Downloads\\DemonSlayer3.MOV");
	
	File bugsbunny1 = new File("C:\\Users\\user\\Downloads\\BugsBunny1.MOV");
	File bugsbunny2 = new File("C:\\Users\\user\\Downloads\\BugsBunny2.MOV");
	File bugsbunny3 = new File("C:\\Users\\user\\Downloads\\BugsBunny3.MOV");
	
	//then, we use these file paths to create episodes in their repsective TVShows
	yugiohShow.createAndAddEpisode(yugioh1, "The Heart of the Cards");
	yugiohShow.createAndAddEpisode(yugioh2, "The Gauntlet Is Thrown");
	yugiohShow.createAndAddEpisode(yugioh3, "Journey to the Duelist Kingdom");
	
	demonSlayerShow.createAndAddEpisode(demonslayer1, "Cruelty");
	demonSlayerShow.createAndAddEpisode(demonslayer2, "Trainer Sakonji Urokodaki");
	demonSlayerShow.createAndAddEpisode(demonslayer3, "Sabito and Makomo");
	
	bugsBunnyShow.createAndAddEpisode(bugsbunny1, "Porky's Hare Hunt");
	bugsBunnyShow.createAndAddEpisode(bugsbunny2, "Prest-O Change-O");
	bugsBunnyShow.createAndAddEpisode(bugsbunny3, "Hare-um Scare-um");
	
	//Now, we finally get to the part of filtering out our TVShows and episodes.
	//We want to filter out the first episodes from all the WarnerBrothers studio that are EITHER in English or French.
	//This indicates that we want to first use the FilterCollectionConjunction class to filter out the first episode from all the WarnerBrothers studio TVShows
	//Then, we use the FilterCollectionDisjunction class to filter out all the episodes that we found that are either in English or French
	
	//we initialize the watchlist we want to add the episodes to
	WatchList watchlist = new WatchList("First episodes of all TV shows from WarnerBrothers studio that are in either English or French");
	
	//we first create the two filters for filtering the episode by number and publishing studio
	EpisodeNumberFilterStrategy episodeFilter = new EpisodeNumberFilterStrategy(1);
	PublishingStudioFilterStrategy studioFilter = new PublishingStudioFilterStrategy("WarnerBrothers");
	
	//then we create a conjunction filter that we can use to filter by the studio and the language
	//we add the pre-existing two filters to this conjunction filter 
	FilterCollectionConjunction filterStudioandEpisodeNum = new FilterCollectionConjunction();
	filterStudioandEpisodeNum.addFilter(episodeFilter);
	filterStudioandEpisodeNum.addFilter(studioFilter);
	
	//then we filter each episode individually and add them to an ArrayList that we will further filter
	List<Episode> passedFirstFilter = new ArrayList<>();
	
	for (int i = 0; i<yugiohShow.getTotalCount(); i++) {
		Episode episode = yugiohShow.getEpisode(i);
		if (filterStudioandEpisodeNum.filter(episode)) {
			passedFirstFilter.add(episode);
		}
	}
	
	for (int i = 0; i<demonSlayerShow.getTotalCount(); i++) {
		Episode episode = demonSlayerShow.getEpisode(i);
		if (filterStudioandEpisodeNum.filter(episode)) {
			passedFirstFilter.add(episode);
		}
	}
	
	for (int i = 0; i<bugsBunnyShow.getTotalCount(); i++) {
		Episode episode = bugsBunnyShow.getEpisode(i);
		if (filterStudioandEpisodeNum.filter(episode)) {
			passedFirstFilter.add(episode);
		}
	}
	
	//now that we have all the episodes filtered out, let us check the contents of the array
	//they should all be episode number 0 published by WarnerBrothers
	for (Episode episode : passedFirstFilter) {
		System.out.println("This is episode " + (episode).getEpisodeNumber() + " from the show " + episode.getTVShow() + " published by " + episode.getStudio() + " in " + episode.getLanguage());
	}
	//this should print out the following: 
		//This is episode 0 from the show Yu-Gi-Oh! published by WarnerBrothers in ENGLISH
		//This is episode 0 from the show Bugs Bunny published by WarnerBrothers in ANCIENT_GREEK
	
	
	//now we create a disjunction filter that allows us to attain watchables in both English and French
	//we filter all the episodes in the array that stores the episodes that passed the first conjunction filter
	LanguageFilterStrategy englishFilter = new LanguageFilterStrategy(Language.ENGLISH);
	LanguageFilterStrategy frenchFilter = new LanguageFilterStrategy(Language.FRENCH);
	
	FilterCollectionDisjunction filterEnglishOrFrench = new FilterCollectionDisjunction();
	filterEnglishOrFrench.addFilter(englishFilter);
	filterEnglishOrFrench.addFilter(frenchFilter);
	
	for (Episode episode : passedFirstFilter) {
		if (filterEnglishOrFrench.filter(episode)) {
			watchlist.addWatchable(episode);
		}
	}
	
	//now, in watchlist there should only be episodes that are the first episodes of TV shows published by WarnerBrothers that are in English or French.
	//in this case, there should only be ONE EPISODE : the first episode in Yu-Gi-Oh!
	Iterator<Watchable> watchlistIterator = watchlist.iterator();
	
	while (watchlistIterator.hasNext()) {
		Episode episode = (Episode) watchlistIterator.next();
		System.out.println("This is episode " + episode.getEpisodeNumber() + " from the show " + episode.getTVShow() + " published by " + episode.getStudio() + " in " + episode.getLanguage());
	}
	//this should print out the following:
		//This is episode 0 from the show Yu-Gi-Oh! published by WarnerBrothers in ENGLISH
	
	
	}
}
