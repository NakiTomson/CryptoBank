package com.example.presentation.ui.bottombar.tabs.home.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.entity.AuthorizationType
import com.example.entity.CardEntity
import com.example.entity.CategoryTransactionType
import com.example.entity.UserEntity
import com.example.presentation.core_compose.CircularProgressBar
import com.example.presentation.theme.Black300
import com.example.presentation.theme.White100
import com.example.presentation.theme.White400
import com.example.presentation.ui.bottombar.tabs.home.dto.BankCard
import com.example.presentation.ui.bottombar.tabs.home.dto.transaction.BankTransactionCategory
import com.example.presentation.ui.bottombar.tabs.home.dto.transaction.BankTransaction
import com.example.presentation.ui.bottombar.tabs.home.model.HomeViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState


@Composable
@Preview(showBackground = true)
private fun HomeScreenPreview() {
    val cardEntity = CardEntity()
    val transactionType = listOf(
        BankTransactionCategory(CategoryTransactionType.Recent),
        *cardEntity.transactions.map { BankTransaction(it) }.toTypedArray()
    )
    val cards = mutableListOf(BankCard(cardEntity, transactionType))
    val user = UserEntity("1", "", AuthorizationType.Null, name = "Dmitry")
    HomeScreen(cards = { cards }, user = { user })
}

@Composable
fun HomeRoute(
    onHomeClicked: () -> Unit = {},
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.stateFlow.collectAsStateWithLifecycle()
    val userState = state.value.user.collectAsStateWithLifecycle()
    val cardsState = state.value.cards.collectAsStateWithLifecycle()
    val isUserLoading = state.value.isUserLoading.collectAsStateWithLifecycle()
    val isBankCardsLoading = state.value.isBankCardsLoading.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = viewModel, block = {

    })
    HomeScreen(
        { userState.value },
        { cardsState.value },
        { isBankCardsLoading.value }
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
internal fun HomeScreen(
    user: () -> UserEntity,
    cards: () -> List<BankCard>,
    isBackCardLoading: () -> Boolean = { false },
) {
    Box(
        modifier = Modifier
            .background(White400)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressBar(
            Modifier.align(Alignment.Center),
            isLoading = isBackCardLoading,
            colorProgress = White100,
            colorIndicator = Black300
        )
        val pagerState: PagerState = rememberPagerState()

        val cardsModifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(top = 20.dp)

        val cardOptionsModifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(top = 20.dp, bottom = 10.dp)

        val transactionModifier = Modifier
            .padding(top = 10.dp, start = 16.dp, end = 16.dp)

        val bankCards = cards().ifEmpty { return }
        val bankCard = bankCards[pagerState.currentPage]

        LazyColumn(modifier = Modifier.fillMaxSize(), rememberLazyListState()) {
            item {
                CardsScreen(cardsModifier, pagerState, user, cards)
            }
            stickyHeader {
                CardOptions(cardOptionsModifier)
            }
            items(bankCard.transactions, key = { it.id }) {
                when (it) {
                    is BankTransactionCategory ->
                        TitleTypeTransactionItem(transactionModifier, it.category)

                    is BankTransaction ->
                        TransactionItem(transactionModifier, it.entity, bankCard.card.paymentType)
                }
            }
        }

    }
}

