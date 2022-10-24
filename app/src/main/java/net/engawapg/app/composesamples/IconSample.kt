package net.engawapg.app.composesamples

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp

val iconList = listOf(
    Icons.Default.Add,
    Icons.Default.AccountBox,
    Icons.Default.AccountCircle,
    Icons.Default.AddCircle,
    Icons.Default.List,
    Icons.Default.ArrowBack,
    Icons.Default.ArrowDropDown,
    Icons.Default.ArrowForward,
    Icons.Default.Build,
    Icons.Default.Call,
    Icons.Default.Check,
    Icons.Default.CheckCircle,
    Icons.Default.Clear,
    Icons.Default.Close,
    Icons.Default.Create,
    Icons.Default.DateRange,
    Icons.Default.Delete,
    Icons.Default.Done,
    Icons.Default.Edit,
    Icons.Default.Email,
    Icons.Default.ExitToApp,
    Icons.Default.Face,
    Icons.Default.Favorite,
    Icons.Default.FavoriteBorder,
    Icons.Default.Home,
    Icons.Default.Info,
    Icons.Default.KeyboardArrowDown,
    Icons.Default.KeyboardArrowLeft,
    Icons.Default.KeyboardArrowRight,
    Icons.Default.KeyboardArrowUp,
    Icons.Default.LocationOn,
    Icons.Default.Lock,
    Icons.Default.MailOutline,
    Icons.Default.Menu,
    Icons.Default.MoreVert,
    Icons.Default.Notifications,
    Icons.Default.Person,
    Icons.Default.Phone,
    Icons.Default.Place,
    Icons.Default.PlayArrow,
    Icons.Default.Refresh,
    Icons.Default.Search,
    Icons.Default.Send,
    Icons.Default.Settings,
    Icons.Default.Share,
    Icons.Default.ShoppingCart,
    Icons.Default.Star,
    Icons.Default.ThumbUp,
    Icons.Default.Warning,
    Icons.Default.SportsBar,
).sortedBy { icon -> icon.name }

@Composable
fun IconSample() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = WindowInsets.safeDrawing.asPaddingValues(),
    ) {
        items(iconList) { icon ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = rememberVectorPainter(image = icon),
                    contentDescription = null,
                    modifier = Modifier.padding(4.dp),
                )
                Text(
                    text = icon.name.split(".").last()
                )
            }
        }
    }
}