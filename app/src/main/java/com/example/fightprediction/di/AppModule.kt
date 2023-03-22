package com.example.fightprediction.di

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.fightprediction.api.ApiInterface
import com.example.fightprediction.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        app: Application
    ) = Room.databaseBuilder(app, AppDatabase::class.java, "fight_prediction_database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideUserDao(db: AppDatabase) = db.userDao()

    @Provides
    fun provideBetDao(db: AppDatabase) = db.betDao()

    @Provides
    fun provideEventDao(db: AppDatabase) = db.eventDao()

    @Provides
    fun provideFightDao(db: AppDatabase) = db.fightDao()

    @Provides
    fun provideFighterDao(db: AppDatabase) = db.fighterDao()

    @Provides
    fun provideLeagueDao(db: AppDatabase) = db.leagueDao()

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
       return OkHttpClient
           .Builder()
           .readTimeout(60, TimeUnit.SECONDS)
           .connectTimeout(60, TimeUnit.SECONDS)
           .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(ApiInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiInterface =
        retrofit.create(ApiInterface::class.java)

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, `name` TEXT, " +
                "PRIMARY KEY(`id`))")
    }
}


@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope