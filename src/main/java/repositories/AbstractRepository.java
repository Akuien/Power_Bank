package repositories;

import data.PersistenceData;

public abstract class bstractRepository {
    //We created this abstract class so that the data does not get
    //rebooted every time we get to use each repository
    //Conclusion: we can share data between repositories
    protected static PersistenceData persistenceData = new PersistenceData();
}
