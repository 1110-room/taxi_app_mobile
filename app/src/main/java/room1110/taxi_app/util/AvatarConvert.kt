package room1110.taxi_app.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import room1110.taxi_app.data.User

object AvatarConvert {
    fun editAvatarBitmap(userView: ImageView, user: User) {
        val avatarBytes = user.getAvatar()
        if (avatarBytes != null) {
            userView.setImageBitmap(byteArrayToBitmap(avatarBytes))
        }
    }

    private fun byteArrayToBitmap(data: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(data, 0, data.size)
    }
}