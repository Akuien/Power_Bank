package repositories;

import data.PersistenceData; // What is this? (Aleksey)

public abstract class AbstractRepository {

    // We created this abstract class so that the data does not get
    // rebooted every time we get to use each repository
    // Conclusion: we can share data between repositories
    // This is the "main" Repository, as the other repositories inherit from it.

    protected static PersistenceData persistenceData = new PersistenceData();
}
