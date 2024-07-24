package com.codingbot.news.domain;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.codingbot.news.domain.model.ArticleStoredData;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ArticleDAO_Impl implements ArticleDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ArticleStoredData> __insertionAdapterOfArticleStoredData;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllArticle;

  private final SharedSQLiteStatement __preparedStmtOfUpdateIsSelected;

  public ArticleDAO_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfArticleStoredData = new EntityInsertionAdapter<ArticleStoredData>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `ArticleTable` (`id_source`,`name_source`,`author`,`title`,`description`,`url`,`urlToImage`,`publishedAt`,`content`,`isSelected`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ArticleStoredData entity) {
        if (entity.getId_source() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId_source());
        }
        statement.bindString(2, entity.getName_source());
        if (entity.getAuthor() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getAuthor());
        }
        statement.bindString(4, entity.getTitle());
        if (entity.getDescription() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getDescription());
        }
        statement.bindString(6, entity.getUrl());
        if (entity.getUrlToImage() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getUrlToImage());
        }
        statement.bindString(8, entity.getPublishedAt());
        if (entity.getContent() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getContent());
        }
        final int _tmp = entity.isSelected() ? 1 : 0;
        statement.bindLong(10, _tmp);
      }
    };
    this.__preparedStmtOfDeleteAllArticle = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM ArticleTable";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateIsSelected = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE ArticleTable SET isSelected = ? WHERE publishedAt = ?";
        return _query;
      }
    };
  }

  @Override
  public void insertArticle(final ArticleStoredData article) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfArticleStoredData.insert(article);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllArticle() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllArticle.acquire();
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteAllArticle.release(_stmt);
    }
  }

  @Override
  public void updateIsSelected(final boolean isSelected, final String publishedAt) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateIsSelected.acquire();
    int _argIndex = 1;
    final int _tmp = isSelected ? 1 : 0;
    _stmt.bindLong(_argIndex, _tmp);
    _argIndex = 2;
    _stmt.bindString(_argIndex, publishedAt);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfUpdateIsSelected.release(_stmt);
    }
  }

  @Override
  public List<ArticleStoredData> getArticles() {
    final String _sql = "SELECT * FROM ArticleTable";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfIdSource = CursorUtil.getColumnIndexOrThrow(_cursor, "id_source");
      final int _cursorIndexOfNameSource = CursorUtil.getColumnIndexOrThrow(_cursor, "name_source");
      final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "author");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
      final int _cursorIndexOfUrlToImage = CursorUtil.getColumnIndexOrThrow(_cursor, "urlToImage");
      final int _cursorIndexOfPublishedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "publishedAt");
      final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
      final int _cursorIndexOfIsSelected = CursorUtil.getColumnIndexOrThrow(_cursor, "isSelected");
      final List<ArticleStoredData> _result = new ArrayList<ArticleStoredData>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final ArticleStoredData _item;
        final String _tmpId_source;
        if (_cursor.isNull(_cursorIndexOfIdSource)) {
          _tmpId_source = null;
        } else {
          _tmpId_source = _cursor.getString(_cursorIndexOfIdSource);
        }
        final String _tmpName_source;
        _tmpName_source = _cursor.getString(_cursorIndexOfNameSource);
        final String _tmpAuthor;
        if (_cursor.isNull(_cursorIndexOfAuthor)) {
          _tmpAuthor = null;
        } else {
          _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
        }
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        final String _tmpUrl;
        _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
        final String _tmpUrlToImage;
        if (_cursor.isNull(_cursorIndexOfUrlToImage)) {
          _tmpUrlToImage = null;
        } else {
          _tmpUrlToImage = _cursor.getString(_cursorIndexOfUrlToImage);
        }
        final String _tmpPublishedAt;
        _tmpPublishedAt = _cursor.getString(_cursorIndexOfPublishedAt);
        final String _tmpContent;
        if (_cursor.isNull(_cursorIndexOfContent)) {
          _tmpContent = null;
        } else {
          _tmpContent = _cursor.getString(_cursorIndexOfContent);
        }
        final boolean _tmpIsSelected;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsSelected);
        _tmpIsSelected = _tmp != 0;
        _item = new ArticleStoredData(_tmpId_source,_tmpName_source,_tmpAuthor,_tmpTitle,_tmpDescription,_tmpUrl,_tmpUrlToImage,_tmpPublishedAt,_tmpContent,_tmpIsSelected);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public ArticleStoredData getArticleByRow(final String publishedAt) {
    final String _sql = "SELECT * FROM ArticleTable WHERE publishedAt = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, publishedAt);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfIdSource = CursorUtil.getColumnIndexOrThrow(_cursor, "id_source");
      final int _cursorIndexOfNameSource = CursorUtil.getColumnIndexOrThrow(_cursor, "name_source");
      final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "author");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
      final int _cursorIndexOfUrlToImage = CursorUtil.getColumnIndexOrThrow(_cursor, "urlToImage");
      final int _cursorIndexOfPublishedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "publishedAt");
      final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
      final int _cursorIndexOfIsSelected = CursorUtil.getColumnIndexOrThrow(_cursor, "isSelected");
      final ArticleStoredData _result;
      if (_cursor.moveToFirst()) {
        final String _tmpId_source;
        if (_cursor.isNull(_cursorIndexOfIdSource)) {
          _tmpId_source = null;
        } else {
          _tmpId_source = _cursor.getString(_cursorIndexOfIdSource);
        }
        final String _tmpName_source;
        _tmpName_source = _cursor.getString(_cursorIndexOfNameSource);
        final String _tmpAuthor;
        if (_cursor.isNull(_cursorIndexOfAuthor)) {
          _tmpAuthor = null;
        } else {
          _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
        }
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        final String _tmpUrl;
        _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
        final String _tmpUrlToImage;
        if (_cursor.isNull(_cursorIndexOfUrlToImage)) {
          _tmpUrlToImage = null;
        } else {
          _tmpUrlToImage = _cursor.getString(_cursorIndexOfUrlToImage);
        }
        final String _tmpPublishedAt;
        _tmpPublishedAt = _cursor.getString(_cursorIndexOfPublishedAt);
        final String _tmpContent;
        if (_cursor.isNull(_cursorIndexOfContent)) {
          _tmpContent = null;
        } else {
          _tmpContent = _cursor.getString(_cursorIndexOfContent);
        }
        final boolean _tmpIsSelected;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsSelected);
        _tmpIsSelected = _tmp != 0;
        _result = new ArticleStoredData(_tmpId_source,_tmpName_source,_tmpAuthor,_tmpTitle,_tmpDescription,_tmpUrl,_tmpUrlToImage,_tmpPublishedAt,_tmpContent,_tmpIsSelected);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
