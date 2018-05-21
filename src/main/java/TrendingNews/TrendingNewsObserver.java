package TrendingNews;

import SummarizerAlgorithm.Api_Client;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;

import java.io.IOException;
import java.util.*;

/**
 * Created by SasankaKudagoda on 3/31/18.
 */

public class TrendingNewsObserver {
    public static void main(String[] args) {
        ArrayList news = new ArrayList();
        news.add("Amazon said after the vote that it would continue construction on one building it had paused during City Council deliberations, but is apprehensive about future growth in Seattle and continues evaluating options for another building it had committed to leasing.\n" +
                "The group had no political contributions listed as of Friday.\n" +
                "Spady is listed as the group’s secretary.\n" +
                "Actual spending decisions would be made as part of the city budget process in the fall.\n" +
                "Mayor Jenny Durkan signed the ordinance on Wednesday.\n" +
                "If the referendum gets enough signatures, the city council must place it on the next scheduled election ballot.\n" +
                "A primary election is scheduled for Aug.\n" +
                "7, though the deadline to place measures on the ballot for that was May 11.\n" +
                "The next general election is Nov.\n" +
                "6, when voters will already be presented with a property tax levy to fund education.");
        news.add("The 2018 Craft Beer of China Exhibition features breweries like Rasenburg Beer, Myth Monkey Brewing, Lazy Taps, Goose Island and Boxing Cat Brewery that are sharing tips on the latest technology and sales trends as Chinese shift from legacy brews to more experimental, refined, and expensive flavors.\n" +
                "From taps at the expo flowed creative mixes of flavors and traditions, a swirling cocktail of Chinese ingredients, barley, hops and spices from around the world.\n" +
                "SHANGHAI — “Panda Beer,” ‘’Little General,” ‘’Flying Fist IPA,” and “Mandarin Wheat” are among the offerings on tap at a craft beer exhibition this week in Shanghai dedicated to expanding the palette of Chinese consumers and promoting sales of high-end brews.\n" +
                "“After drinking it (craft beer), it feels much better than the domestic industry beer, and then you just can’t leave it,” said Yu Shiqi, a 40-year old craft beer consumer at the expo who dreams of brewing his own.\n" +
                "There’s money to be made in China, which drinks a quarter of all beer worldwide, and small-batch brewers and giant multinationals are cashing in.\n" +
                "Though craft beer is far from upstaging local beer behemoths like Tsingtao that dominate the $28 billion national beer market, it is rising in popularity as small breweries open up in China’s major metropolitan areas like Beijing, Shanghai and Shenzhen.\n" +
                "Craft beers are typically more expensive than mass-market, low-alcohol content brews like Budweiser and China’s Yanjing.\n" +
                "But as China’s middle class grows, so too does its tastes for finer products.\n" +
                "A couple of years ago, craft beer made up only 0.3 percent of total beer consumption.\n" +
                "It has since risen to about 5 percent, said Darren Guo, one of the exhibition’s organizers, who expect to see 30 percent growth in the craft beer market every year until 2020.\n");
        news.add("The bad news, she was glancing at her phone instead of looking ahead at the road.\n" +
                "She took her hands off the wheel for what police say was 80 seconds as the car sped along on Autopilot.\n" +
                "These new features are basically a combination of warning systems, like letting you know whether cars are approaching in a nearby lane, and advanced cruise control, which monitors the speed of cars ahead and slows accordingly.\n" +
                "Some automakers also offer lane keeping, which keeps a car centered by reading lane markings.\n" +
                "If totally ignored, most systems are programmed to shut off, and in some cases bring the car to a stop.\n" +
                "\"Can humans be entrusted with tech that can lull us into a false sense of security and cause us to ignore automaker warnings about their fallibility?\n" +
                "And much more forceful than originally stated.\n" +
                "It never gets tired, never has something to drink, never argues with someone in the car,\" Musk said at a press conference when Autopilot was added via a software update to Tesla vehicles in 2015.\n" +
                "In other tech news this week\n" +
                "Yes, Laurel vs.\n");
        news.add("Federal Communications Commission said on Friday it was referring reports that a website flaw could have allowed the location of mobile phone customers to be tracked to its enforcement bureau to investigate.\n" +
                "Senator Ron Wyden, an Oregon Democrat, on Friday had urged the FCC to investigate, saying on Twitter that a “hacker could have used this site to know when you were in your house so they would know when to rob it.\n" +
                "Robert Xiao, a researcher at Carnegie Mellon University, said a flaw in a demo tool from LocationSmart could have been used to track anyone.\n" +
                "The company is committed to “continuous improvement of its information privacy and security measures,” she said.\n" +
                "Several published reports said Securus is getting its data through an intermediary of LocationSmart.\n" +
                "If we learn that a vendor does not adhere to our policy we will take appropriate action.”\n" +
                "Sprint said it is conducting an internal review of the issue.\n" +
                "The company also said it has “no direct business relationship with LocationSmart,” adding it is ready to work with law enforcement and vendors to reinstate the service as soon as possible.\n" +
                "major mobile carriers, saying the practice “exposes millions of Americans to potential abuse and unchecked surveillance by the government.”\n" +
                "Reporting by David Shepardson; editing by Chizu Nomiyama and G Crosse\n" +
                "All quotes delayed a minimum of 15 minutes.");
        news.add(" Ron Wyden called on the FCC chairman to recuse himself from an investigation involving a prison phone company that’s been accused of helping police circumvent warrants for acquiring cellphone location data.\n" +
                "The FCC chairman, Ajit Pai, represented Securus while employed at law firm Jenner & Block, LLP, just prior to his confirmation as an FCC commissioner in 2012.\n" +
                "Pai to lead this investigation,” Sen.\n" +
                "Wyden, Democrat of Oregon, said in a statement to Gizmodo.\n" +
                "“I call on Mr.\n" +
                "Pai to do the responsible thing and recuse himself from the investigation.”\n" +
                "The FCC did not respond to a request for comment.\n" +
                "This is typically done for marketing purposes or to provide roadside assistance; however, Securus had instead been supplying the data to police, who might otherwise require a warrant.\n" +
                "“The only real surprise is that it took this long for the public to learn that the wireless carriers and their business partners were demonstrating such a total disregard for Americans’ privacy and safety.”\n" +
                "Securus obtained its data from a mobile marketing firm called 3Cinteractive, which in turn received it from LocationSmart, a “location aggregator,” according to prison documents the Times obtained.\n" +
                "federal appeals court in June.\n" +
                "Prior to the cap, private telecom companies were charging up to $14 per minute for inmate calls.\n");
        news.add("Inspired by apps he’d seen in that country, Mr.\n" +
                "Spiegel wanted to create a new version separating users’ friends’ content from the professional media.\n" +
                "Huddling on the plane with one of his top designers, Will Wu, Mr.\n" +
                "Spiegel hashed out a design for the future of the app.\n" +
                "Some designers told the CEO they were hesitant about releasing it, but Mr.\n" +
                "Spiegel decided to go ahead anyway.\n" +
                "According to the report, team members would work on a piece of the project for a time, only to find out that someone else was working on the same thing.\n" +
                "When one employee suggested they were going to schedule a meeting with Spiegel to explain the issues the project was facing, they were told by a manager that it was a bad idea.\n" +
                "The team missed its deadline, but the redesign did eventually launch in February.\n" +
                "It did away with the chronological timeline that differentiated Snapchat from competitors.");
        news.add("Campbell Soup CEO Denise Morrison is out in an abrupt departure that many industry insiders expected to come, but few thought would happen so swiftly.\n" +
                "Morrison, who had been in the seat since 2011, notified employees of her resignation within the past 48 hours, sources tell CNBC, requesting anonymity because the information is confidential.\n" +
                "The board was unhappy with its quarterly performance, one in a string of painful misses the company has had under her watch.\n" +
                "\"We delivered results that were below expectations, both ours and yours,\" interim CEO Keith McLoughlin told analysts in a Friday morning call.\n" +
                "Campbell continues to face slowing — and in some segments declining — sales.\n" +
                "Older consumers too are increasingly paying attention to the nature of the food they are eating.\n" +
                "It is also lowering its expectations for 2018 earnings per share, to a decline of 3 percent to 1 percent, down from a rise of 2 percent to 4 percent.\n" +
                "The business, which includes fresh food and drinks brand Bolthouse Farms, Garden Fresh Gourmet salsa and refrigerated soup, had an operating loss of $19 million for the quarter.\n" +
                "The same quarter last year it reported earnings of $1 million.\n" +
                "But the brand's carrot business was plagued with quality issues driven by a Californian drought.\n");
        news.add("AMD, Qualcomm shares break trend, trade higher Friday\n" +
                "Applied Materials Inc.\n" +
                "Of the 27 analysts that cover Applied Materials, 21 have overweight or buy ratings and six have hold ratings, according to FactSet data.\n" +
                "Following earnings, 17 analysts commented on the stock with six lowering their price targets and three raising them.\n" +
                "Other notable decliners on Friday included Entegris Inc.\n" +
                "Not all chip makers were down Friday, however, a few outliers traded higher.\n" +
                "Advanced Micro Devices Inc.\n" +
                "initiated coverage of the stock at a buy and a $18 price target.\n" +
                "Qualcomm Inc.\n" +
                "Join the conversation\n" +
                "Copyright © 2018 MarketWatch, Inc.\n" +
                "led semiconductor-related stocks to losses Friday and to a weekly decline after the chip-maker materials supplier’s outlook indicated a possible slowing in the recently hot sector.\n");
        news.add(" Russell 2000 ends at a record for a third straight session\n" +
                "U.S.\n" +
                "stocks closed mostly lower on Friday, with major indexes posting a weekly decline as investors grappled with lingering uncertainty over trade negotiations between the U.S.\n" +
                "and China, as well as bond yields that climbed this week to the highest level since 2011.\n" +
                "The index has risen in 11 of the past 14 sessions, and it rose 1.3% for the week.\n" +
                "That marked its third straight positive week, its longest such streak since January.\n" +
                "Trade talks between the U.S.\n" +
                "and China in Washington have been in focus throughout the week, with little clarity on the status of the negotiations.\n" +
                "On Thursday, several news outlets reported that China had made an offer to cut its trade surplus with the U.S.\n" +
                "by $200 billion, but a China official on Friday denied that an offer had been made.\n" +
                "The psychologically important level of 3% comes with the idea for some that equities could become less attractive.");
        news.add("At Norwegian Airlines, only 1% of the pilots are women.\n" +
                "Discrimination in commercial aviation is as old as the industry.\n" +
                "American Airlines hired pilot David Harris in 1964; Green got a job with Continental the following year.\n" +
                "It took several years more before American Airlines pilot Jill E.\n" +
                "Brown became the first black woman to pilot a major US commercial airline, in 1978.\n" +
                "To fix that, the industry should perhaps turn first to groups it has long overlooked.\n" +
                "As Wright and Cave demonstrated, a plane in the hands of trained and accomplished crew members of any race or gender still lands just fine.\n" +
                "On a recent Alaska Airlines flight from San Francisco to Portland, Oregon, captain Tara Wright took the microphone to welcome her passengers onboard—and to alert them that they were part of a milestone moment in the fight for workplace inclusion.\n" +
                "“You’ll be piloted by two African-American female pilots, for the first time in Alaska Airlines history,” she said alongside first officer Mallory Cave, to cheers and applause in the cabin.\n" +
                "“You’re making [history] this morning, whether you’re awake or not.”\n" +
                "It wasn’t the first US commercial flight to be commanded by two African-American women.\n");
        news.add("New data from the United Way Alice Project finds that many families are struggling to get by\n" +
                "As income stagnates, millions of Americans are struggling to make ends meet.\n" +
                "that aims to highlight the number of people who live in poverty.\n" +
                "The project uses standardized measurements to calculate the “bare bones” household budget in each county in each state.\n" +
                "“For too long, the magnitude of financial instability in this country has been understated and obscured by misleading averages and outdated poverty calculations,” said John Franklin, chief executive of the United Way Alice Project.\n" +
                "“It is morally unacceptable and economically unsustainable for our country to have so many hardworking families living paycheck to paycheck.”\n" +
                "In 2017, 44% of people in the U.S.\n" +
                "said they could not cover an unexpected $400 emergency expense or would rely on borrowing or selling something to do so, down from 46% the year before, according to a separate report released last year by the U.S.\n" +
                "The ability to withstand financial disruptions is a “key consideration,” it said.\n" +
                "Bankrate is paid by financial institutions whenever users click on display advertisements or on rate table listings enhanced with features like logos, navigation links, and toll free numbers.\n" +
                "Join the conversation");
        news.add("She has told the president that the Amazon relationship is beneficial for the Postal Service and gave him a set of slides that showed the variety of companies, in addition to Amazon, that also partner for deliveries.\n" +
                "Amazon primarily uses the Postal Service for the “last mile” of its deliveries.\n" +
                "It is not known how much Amazon pays the Postal Service each year and what percentage of its items are shipped via the Postal Service.\n" +
                "[After Amazon opposition, Seattle passes compromise tax to fund homeless services]\n" +
                "The Postal Service is overseen by a board of nine governors, which pick the postmaster general and the deputy postmaster general.\n" +
                "Amazon has a multiyear contract with the Postal Service, and it is not clear how quickly it could be changed.\n" +
                "Moore says he has told White House officials that Amazon is paying the Postal Service plenty for its services and in fact helping the agency survive.\n" +
                "President Trump has personally pushed U.S.\n" +
                "And last month, his critiques culminated in the signing of an executive order mandating a government review of the financially strapped Postal Service that could lead to major changes in the way it charges Amazon and others for package delivery.\n" +
                "company in terms of market capitalization.\n" +
                "For more than three years, Trump has fumed publicly and privately about the giant commerce and services company and its founder Jeffrey P.\n");
        news.add("\"It's the only way we can think of to address the chronic traffic issues in major cities,\" Musk said at the event.\n" +
                "Elon Musk and SpaceX and Boring Company director Steve Davis talk about digging in Los Angeles.\n" +
                "Elon Musk finally pulled back the curtain on where The Boring Company is headed.\n" +
                "It's a possible future that is far from, well, boring.\n" +
                "The comments, though, provide the most detailed view yet, after two years of teasing, of what Musk wants to do with the vast underground tunnels The Boring Company is planning.\n" +
                "Musk created the company, which has spent the last year digging (or \"boring\") tunnels under Los Angeles, to further his vision of creating a new form of transportation -- and to get out of that nasty LA traffic he's famously complained about.\n" +
                "Musk tweeted a week ago that his first LA tunnel is nearly completed.\n" +
                "Musk previously said that The Boring Company is involved in proposed Hyperloop projects, including one for the US East Coast.\n" +
                "After proposing, in 2012, the idea of flinging pressurized capsules through tubes at insane speeds, Musk initially let other startups run with the idea.\n" +
                "But last year, Boring too got approval to build a Hyperloop between New York and Washington, DC, signaling his intentions.");

    }

    public static HashMap<String, Integer> calculateWordOccurance() {
//        ArrayList keywords = new ArrayList();
        HashMap<String,Integer> keywords  = new HashMap<>();
        keywords.put("Jenny Durkan",10);
        keywords.put("Spady",3);
        keywords.put("Amazon",4);
        keywords.put("Seattle",8);
        keywords.put("Shanghai",2);
        keywords.put("China",9);
        keywords.put("China Exhibition",3);
        keywords.put("Goose Island",7);
        keywords.put("Boxing Cat Brewery",2);
        keywords.put("Darren Guo",1);
        keywords.put("Yu Shiqi",2);
        keywords.put("Beijing",3);
        keywords.put("Shenzhen",4);
        keywords.put("Senator Ron Wyden",2);
        keywords.put("Carnegie Mellon University",2);
        keywords.put("Robert Xiao",2);
        keywords.put("Twitter",4);
        keywords.put("Securus",2);
        keywords.put("Oregon",6);
        keywords.put("FCC",5);
        keywords.put("Ajit Pai",3);
        keywords.put("Jenner & Block",1);
        keywords.put("Spiegel",1);
        keywords.put("Elon Musk",9);
        keywords.put("Snapchat",5);
        keywords.put("Denise Morrison",3);
        keywords.put("Campbell Soup",6);
        keywords.put("Keith McLoughlin",5);
        keywords.put("CNBC",6);
        keywords.put("Garden Fresh Gourmet",2);
        keywords.put("Bolthouse Farms",2);
        keywords.put("Qualcomm Inc",1);
        keywords.put("Advanced Micro Devices Inc",1);
        keywords.put("Russell",4);
        keywords.put("American Airlines",2);
        keywords.put("Norwegian Airlines",2);
        keywords.put("Tara Wright",2);
        keywords.put("Alaska Airlines",4);
        keywords.put("Mallory Cave",1);
        keywords.put("Alaska",1);
        keywords.put("David Harris",3);
        keywords.put("The Postal Service",3);
        keywords.put("President Trump",5);
        keywords.put("Seattle",5);
        keywords.put("White House",4);
        keywords.put("The Boring Company",6);
        keywords.put("Los Angeles",3);
        keywords.put("LA tunnel",2);
        keywords.put("Steve Davis",2);
        keywords.put("DC",6);
        keywords.put("New York",5);
        return keywords;
    }

    public static void calculateWordOccurance(ArrayList news) throws IOException, InterruptedException {
        for (int i = 0; i < news.size(); i++) {
            Api_Client api_client = new Api_Client();
            AnalysisResults response = api_client.getExtractedEntities((String) news.get(i));
            List entityList = response.getEntities();
        }
        ArrayList keywords = new ArrayList();
        keywords.add("Jenny Durkan");
        keywords.add("Spady");
        keywords.add("Amazon");
        keywords.add("Seattle");
        keywords.add("Shanghai");
        keywords.add("China");
        keywords.add("China Exhibition");
        keywords.add("Goose Island");
        keywords.add("Boxing Cat Brewery");
        keywords.add("Darren Guo");
        keywords.add("Yu Shiqi");
        keywords.add("Beijing");
        keywords.add("Shenzhen");
        keywords.add("Senator Ron Wyden");
        keywords.add("Carnegie Mellon University");
        keywords.add("Robert Xiao");
        keywords.add("Twitter");
        keywords.add("Securus");
        keywords.add("Oregon");
        keywords.add("FCC");
        keywords.add("Ajit Pai");
        keywords.add("Jenner & Block");
        keywords.add("Spiegel");
        keywords.add("Elon Musk");
        keywords.add("Snapchat");
        keywords.add("Denise Morrison");
        keywords.add("Campbell Soup");
        keywords.add("Keith McLoughlin");
        keywords.add("CNBC");
        keywords.add("Garden Fresh Gourmet");
        keywords.add("Bolthouse Farms");
        keywords.add("Qualcomm Inc");
        keywords.add("Advanced Micro Devices Inc");
        keywords.add("Russell");
        keywords.add("American Airlines");
        keywords.add("Norwegian Airlines");
        keywords.add("Tara Wright");
        keywords.add("Alaska Airlines");
        keywords.add("Mallory Cave");
        keywords.add("Alaska");
        keywords.add("David Harris");
        keywords.add("The Postal Service");
        keywords.add("President Trump");
        keywords.add("Seattle");
        keywords.add("White House");
        keywords.add("The Boring Company");
        keywords.add("Los Angeles");
        keywords.add("LA tunnel");
        keywords.add("Steve Davis");
        keywords.add("DC");
        keywords.add("New York");

    }

}
